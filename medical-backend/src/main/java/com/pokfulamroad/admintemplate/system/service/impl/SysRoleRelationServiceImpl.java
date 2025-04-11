package com.pokfulamroad.admintemplate.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.system.entity.domain.SysRoleRelation;
import com.pokfulamroad.admintemplate.system.mapper.SysRoleRelationMapper;
import com.pokfulamroad.admintemplate.system.service.SysRoleRelationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SysRoleRelationServiceImpl extends ServiceImpl<SysRoleRelationMapper, SysRoleRelation>
    implements SysRoleRelationService{

    @Override
    public void removeRoleRelationByUserId(Long userId) {
        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleRelation::getUserId,userId);
        this.remove(queryWrapper);
    }

    @Override
    public List<Long> getAllUserRoleId(Long userId) {
        return this.baseMapper.selectAllRoleIdByUserId(userId);
    }


    @Override
    public List<Long> getGrantedUserIds(Long roleId) {
        LambdaQueryWrapper<SysRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleRelation::getRoleId,roleId);
        List<SysRoleRelation> roleRelationList = this.baseMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(roleRelationList)) return Collections.emptyList();


        return roleRelationList.stream().map(SysRoleRelation::getUserId).toList();
    }


}




