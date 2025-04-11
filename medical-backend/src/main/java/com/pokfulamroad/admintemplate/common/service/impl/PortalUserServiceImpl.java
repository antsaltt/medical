package com.pokfulamroad.admintemplate.common.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.common.entity.PortalUserDto;
import com.pokfulamroad.admintemplate.common.entity.PortalUserRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PortalUser;
import com.pokfulamroad.admintemplate.common.mapper.PortalUserMapper;
import com.pokfulamroad.admintemplate.common.service.PortalUserService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class PortalUserServiceImpl extends ServiceImpl<PortalUserMapper, PortalUser>
        implements PortalUserService {




    @Override
    public Page<PortalUserDto> getUsersPage(PortalUserRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(10).setCurrent(request.getCurrent());
        return this.baseMapper.selectUsersPage(request, objectPage);
    }

    @Override
    public void addUsers(PortalUserRequest request) {
        PortalUser portalUser = BeanUtil.copyProperties(request, PortalUser.class);
        portalUser.setCreateTime(new Date());
        this.baseMapper.insert(portalUser);
    }

    @Override
    public void deleteUsers(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateUsers(PortalUserRequest request) {
        PortalUser portalUser = BeanUtil.copyProperties(request, PortalUser.class);
        this.baseMapper.updateById(portalUser);
    }


    @Override
    public PortalUserDto getUsersOne(Long id) {
        LambdaQueryWrapper<PortalUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PortalUser::getId, id);
        PortalUser portalUser = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(portalUser, PortalUserDto.class);
    }

}




