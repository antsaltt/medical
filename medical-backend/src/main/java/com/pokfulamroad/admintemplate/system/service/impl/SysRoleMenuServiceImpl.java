package com.pokfulamroad.admintemplate.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleMenu;
import com.pokfulamroad.admintemplate.system.service.SysRoleMenuService;
import com.pokfulamroad.admintemplate.system.mapper.SysRoleMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu>
    implements SysRoleMenuService{

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMenusToRole(List<SysRoleMenu> list) {

        if (CollUtil.isEmpty(list)) return;
        SysRoleMenu sysRoleMenu = list.get(0);

        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId,sysRoleMenu.getRoleId());
        this.remove(queryWrapper);
        list.forEach(this.baseMapper::insert);
    }

    @Override
    public List<Long> getMenuIdsByRoleIds(List<Long> roleIds) {
        LambdaQueryWrapper<SysRoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRoleMenu::getRoleId,roleIds);
        List<SysRoleMenu> sysRoleMenus = this.baseMapper.selectList(lambdaQueryWrapper);
        if (CollUtil.isEmpty(sysRoleMenus)) return Collections.emptyList();

        return sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }
}




