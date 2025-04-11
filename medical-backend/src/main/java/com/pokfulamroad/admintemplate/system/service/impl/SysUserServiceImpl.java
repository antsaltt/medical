package com.pokfulamroad.admintemplate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.system.auth.entity.SysLoginUser;
import com.pokfulamroad.admintemplate.system.entity.SysRoleRequest;
import com.pokfulamroad.admintemplate.system.entity.SysUserDto;
import com.pokfulamroad.admintemplate.system.entity.SysUserRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysMenu;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRole;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleRelation;
import com.pokfulamroad.admintemplate.system.entity.domain.SysUser;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import com.pokfulamroad.admintemplate.system.mapper.SysUserMapper;
import com.pokfulamroad.admintemplate.system.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    private final SysRoleRelationService sysRoleRelationService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;

    @Override
    public List<SysUserDto> getUnGrantedUsers(SysRoleRequest sysRoleRequest) {
        List<Long> grantedUserIds = sysRoleRelationService.getGrantedUserIds(sysRoleRequest.getRoleId());
        List<SysUser> sysUsers = this.list();
        List<SysUserDto> sysUserDtoList = BeanUtil.copyToList(sysUsers, SysUserDto.class);
        if (CollUtil.isEmpty(grantedUserIds)) return sysUserDtoList;

        return sysUserDtoList.stream()
                .filter(x -> !grantedUserIds.contains(Long.parseLong(x.getUserId())))
                .toList();

    }

    @Override
    public List<SysUserDto> getGrantedUsers(SysRoleRequest sysRoleRequest) {
        List<Long> grantedUserIds = sysRoleRelationService.getGrantedUserIds(sysRoleRequest.getRoleId());
        if (CollUtil.isEmpty(grantedUserIds)) return Collections.emptyList();

        List<SysUser> sysUsers = this.listByIds(grantedUserIds);
        return BeanUtil.copyToList(sysUsers, SysUserDto.class);
    }

    @Override
    public List<SysUserDto> getUserList(SysUserRequest request) {
        List<SysUser> list = this.list();

        if (CollUtil.isEmpty(list)) return Collections.emptyList();

        List<SysUserDto> sysUserDtoList = BeanUtil.copyToList(list, SysUserDto.class);
        List<Long> userIds = list.stream().map(SysUser::getUserId).toList();

        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysRoleRelation::getUserId,userIds);
        List<SysRoleRelation> relationList = sysRoleRelationService.list(queryWrapper);

        if (CollUtil.isEmpty(relationList)) return Collections.emptyList();

        List<Long> roleIdList = relationList.stream().map(SysRoleRelation::getRoleId).toList();

        LambdaQueryWrapper<SysRole> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(SysRole::getRoleId,roleIdList);
        List<SysRole> list1 = sysRoleService.list(queryWrapper1);

        if (CollUtil.isEmpty(list1)) return Collections.emptyList();


        Map<Long, List<SysRole>> roleIdMap = list1.stream().collect(groupingBy(SysRole::getRoleId));
        Map<Long, List<SysRoleRelation>> relMap = relationList.stream().collect(groupingBy(SysRoleRelation::getUserId));


        sysUserDtoList.forEach(x -> {
            List<SysRoleRelation> relationList1 = relMap.get(Long.parseLong(x.getUserId()));

            if (CollUtil.isEmpty(relationList1)) return;

            relationList1.forEach(rel -> {
                List<SysRole> sysRoles = roleIdMap.get(rel.getRoleId());
                List<String> roleNameList = sysRoles.stream().map(SysRole::getRoleName).toList();
                x.setRoleName(roleNameList);
            });
        });

        return  sysUserDtoList;

    }

    @Override
    public SysLoginUser getUserByAccount(String account) {
        SysUser sysUser = this.baseMapper.selectByAccount(account);
        if (ObjectUtil.isNotEmpty(sysUser)) {
            return BeanUtil.copyProperties(sysUser,SysLoginUser.class);
        }
        return null;
    }

    @Override
    public List<Tree<String>> ownMenu(SysUserRequest request) {
        // 获取角色id 列表
        List<Long> roleIds = this.ownRole(request);
        if (CollUtil.isEmpty(roleIds)) {
            throw new ServiceException("请先为用户分配角色");
        }

        List<Long> menuIds = sysRoleMenuService.getMenuIdsByRoleIds(roleIds);

        List<SysMenu> allMenuList = sysMenuService.list();
        List<SysMenu> menuList = allMenuList.stream().filter(x -> menuIds.contains(x.getMenuId())).toList();

        List<Long> menuParentIds = menuList.stream().map(SysMenu::getParentId).toList();
        List<SysMenu> parentMenus = allMenuList.stream().filter(x -> menuParentIds.contains(x.getMenuId())).toList();

        List<SysMenu> resultMenus = new ArrayList<>();
        resultMenus.addAll(menuList);
        resultMenus.addAll(parentMenus);


        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("orderNum");
        treeNodeConfig.setIdKey("menuId");
        // 最大递归深度
        treeNodeConfig.setDeep(3);



        List<TreeNode<Long>> treeNodeList = resultMenus.stream().map(x -> {
            TreeNode<Long> longTreeNode = new TreeNode<>(x.getMenuId(), x.getParentId(), x.getMenuName(), x.getOrderNum());
            Map<String,Object> map = new HashMap<>();
            map.put("path", x.getPath());
            map.put("defaultMenu", x.getDefaultMenu());
            longTreeNode.setExtra(map);
            return longTreeNode;
        }).toList();


        return TreeUtil.build(treeNodeList, "0", null,
                (treeNode, tree) -> {
                    tree.setId(String.valueOf(treeNode.getId()));
                    tree.setParentId(String.valueOf(treeNode.getParentId()));
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    tree.putExtra("path",treeNode.getExtra().get("path"));
                    tree.putExtra("defaultMenu",treeNode.getExtra().get("defaultMenu"));

//                    // 扩展属性 ...
//                    tree.putExtra("extraField", 666);
//                    tree.putExtra("other", new Object());
                });

    }

    @Override
    public List<Long> ownRole(SysUserRequest request) {
        return sysRoleRelationService.getAllUserRoleId(request.getUserId());
    }

    @Override
    public void userDelete(Long userId) {
        this.removeById(userId);
    }

    @Override
    public void editUser(SysUserRequest request) {
        SysUser sysUser = BeanUtil.copyProperties(request, SysUser.class);
        this.updateById(sysUser);
    }

    @Override
    public SysUserDto getUserOne(SysUserRequest sysUserRequest) {
        SysUser sysUser = this.getById(sysUserRequest.getUserId());
        return BeanUtil.copyProperties(sysUser, SysUserDto.class);
    }

    @Override
    public List<SysRole> getRoleListByIdList(SysUserRequest sysUserRequest) {
        return null;
    }
}




