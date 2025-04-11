package com.pokfulamroad.admintemplate.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.system.entity.SysMenuDto;
import com.pokfulamroad.admintemplate.system.entity.SysMenuRequest;
import com.pokfulamroad.admintemplate.system.entity.domain.SysMenu;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import com.pokfulamroad.admintemplate.system.mapper.SysMenuMapper;
import com.pokfulamroad.admintemplate.system.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Override
    public void saveMenus(SysMenuRequest request) {
        // 防止 传入 id
        // 类型等值的范围合理
        SysMenu sysMenu = BeanUtil.copyProperties(request, SysMenu.class);
        this.save(sysMenu);
    }

    @Override
    public SysMenuDto getMenuOne(SysMenuRequest request) {
        SysMenu sysMenu = this.getById(request.getMenuId());
        return  BeanUtil.copyProperties(sysMenu,SysMenuDto.class);

    }

    @Override
    public void editMenu(SysMenuRequest request) {
        SysMenu sysMenu = BeanUtil.copyProperties(request, SysMenu.class);
        this.updateById(sysMenu);
    }

    @Override
    public void deleteMenu(SysMenuRequest request) {
        SysMenu sysMenu = this.getById(request.getMenuId());
        if ("1".equals(sysMenu.getDefaultMenu())) {
            throw new ServiceException("默认菜单不可删除");
        }

        this.removeById(request.getMenuId());
    }

    @Override
    public List<Tree<String>> getEntryMenuTree() {

        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysMenu::getMenuType,1);
        List<SysMenu> sysMenuList = this.baseMapper.selectList(lambdaQueryWrapper);

        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuName("顶级");
        sysMenu.setMenuId(0L);
        sysMenu.setParentId(-1L);
        sysMenu.setOrderNum(1);
        sysMenuList.add(sysMenu);

        return convertMenuTree(sysMenuList,"-1");
    }

    @Override
    public List<Tree<String>> getMenuTree() {

        List<SysMenu> list = this.list();
        return convertMenuTree(list,"0");
    }


    private List<Tree<String>> convertMenuTree(List<SysMenu> list,String parentId) {
        List<TreeNode<String>> treeNodeList = list.stream().map(x -> {
            TreeNode<String> longTreeNode = new TreeNode<>(String.valueOf(x.getMenuId()), String.valueOf(x.getParentId()), x.getMenuName(), x.getOrderNum());

            Map<String,Object> map = new HashMap<>();
            map.put("path",x.getPath());
            map.put("icon",x.getIcon());
            map.put("orderNum",x.getOrderNum());
            map.put("menuType",x.getMenuType());
            map.put("visible",x.getVisible());
            longTreeNode.setExtra(map);
            return longTreeNode;
        }).toList();

        return TreeUtil.build(treeNodeList, parentId);
    }
 }




