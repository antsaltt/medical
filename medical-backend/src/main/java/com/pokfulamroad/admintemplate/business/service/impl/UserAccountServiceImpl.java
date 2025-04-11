package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.UserAccountRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.UserAccount;
import com.pokfulamroad.admintemplate.business.mapper.UserAccountMapper;
import com.pokfulamroad.admintemplate.business.service.UserAccountService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount>
        implements UserAccountService {


    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public Page<UserAccountDto> getUserAccountPage(UserAccountRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(10).setCurrent(request.getCurrent());
        return this.baseMapper.selectUserAccountPage(request, objectPage);
    }

    @Override
    public void addUserAccount(UserAccountRequest request) {
        UserAccount userAccount = BeanUtil.copyProperties(request, UserAccount.class);
        userAccount.setCreateTime(new Date());
        this.baseMapper.insert(userAccount);
    }

    @Override
    public void deleteUserAccount(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateUserAccount(UserAccountRequest request) {
        UserAccount userAccount = BeanUtil.copyProperties(request, UserAccount.class);
        this.baseMapper.updateById(userAccount);
    }

    @Override
    public void register(UserAccountRequest request, HttpSession httpSession) {

        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccount::getUsername,request.getUsername());
        UserAccount one = this.getOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(one) ) {
            throw new ServiceException("用户名已存在");
        }

        UserAccount userAccount = BeanUtil.copyProperties(request, UserAccount.class);
        this.baseMapper.insert(userAccount);

    }

    @Override
    public String userLogin(UserAccountRequest request, HttpSession httpSession) {
        // 从请求中获取用户名和密码
        String username = request.getUsername();
        String password = request.getPassword();

        // 获取用户实例
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccount::getUsername,username);
        UserAccount one = this.getOne(queryWrapper);

        // 检查用户是否存在且密码是否正确
        if (ObjectUtil.isEmpty(one) || !one.getPassword().equals(password)) {
            throw new ServiceException("账号或密码错误");
        }

        UserAccountDto userAccountDto = BeanUtil.copyProperties(one, UserAccountDto.class);
        // 生成一个随机的UUID作为登录令牌（token）
        String token = UUID.randomUUID().toString();
        String key = "login:token:" + token;

        // 将用户token 和信息存入redis
        stringRedisTemplate.opsForHash().putAll(key,BeanUtil.beanToMap(userAccountDto));
        stringRedisTemplate.expire(key,30, TimeUnit.MINUTES);

        //httpSession.setAttribute("user",one);

        return token;
    }

    @Override
    public UserAccountDto getUserAccountOne(Long id) {
        LambdaQueryWrapper<UserAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserAccount::getId, id);
        UserAccount one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, UserAccountDto.class);
    }

}




