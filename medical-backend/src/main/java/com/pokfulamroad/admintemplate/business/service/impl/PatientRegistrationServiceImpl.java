package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationDto;
import com.pokfulamroad.admintemplate.business.entity.PatientRegistrationRequest;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientRegistration;
import com.pokfulamroad.admintemplate.business.mapper.PatientRegistrationMapper;
import com.pokfulamroad.admintemplate.business.service.PatientRegistrationService;
import com.pokfulamroad.admintemplate.business.utils.UserHolder;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class PatientRegistrationServiceImpl extends ServiceImpl<PatientRegistrationMapper, PatientRegistration>
        implements PatientRegistrationService {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String LOCK_PREFIX = "registration:";


    @Override
    public Page<PatientRegistrationDto> getPatientRegistrationPage(PatientRegistrationRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(10).setCurrent(request.getCurrent());
        return this.baseMapper.selectPatientRegistrationPage(request, objectPage);
    }

    /**
     * 尝试为指定的患者、时间段和日期获取锁。
     * 如果锁未被其他用户持有，则将锁分配给当前用户，并设置锁的过期时间。
     *
     * @param patientId   患者ID，用于指定哪个患者的资源需要锁定。
     * @param timeSection 时间段，表示需要锁定的具体时间段。
     * @param dateStr     日期字符串，表示需要锁定的日期。
     * @param userId      当前请求的用户ID，用于标识哪个用户尝试获取锁。
     */
    private boolean tryLock(String patientId, String timeSection, String dateStr, String userId) {
        // 构建锁的唯一键，基于患者ID、时间段和日期
        String lockKey = LOCK_PREFIX + ":" + patientId + ":" + timeSection + ":" + dateStr;
        // 尝试在Redis中设置锁，如果该键不存在（即锁未被持有），则设置锁并指定过期时间
        Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, userId, 60, TimeUnit.SECONDS);

        return b;
    }


    /**
     * 添加患者注册信息
     *
     * @param request 患者注册请求对象，包含患者ID和时间量子等信息
     * @throws RuntimeException 如果尝试锁定患者预约时段失败，则抛出异常
     */
    @Override
    public void addPatientRegistration(PatientRegistrationRequest request) {

        UserAccountDto user = UserHolder.getUser();
        if (user == null) {
            throw new ServiceException("请先登录");
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String userId = user.getId();

        // 尝试锁定患者预约的时间段
        boolean lock = tryLock(String.valueOf(request.getPatientId()), request.getTimeQuantum(), sf.format(new Date()), userId);
        // 如果锁定失败，则抛出异常
        if (!lock) {
            throw new ServiceException("抱歉！该时段已被预约");
        }

        LambdaQueryWrapper<PatientRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientRegistration::getResumeId, request.getResumeId());
        queryWrapper.eq(PatientRegistration::getTimeQuantum, request.getTimeQuantum());
        queryWrapper.eq(PatientRegistration::getDoctorId, request.getDoctorId());
        queryWrapper.eq(PatientRegistration::getApplyDate, sf.format(new Date()));
        if (this.baseMapper.selectCount(queryWrapper) > 0) {
            throw new ServiceException("抱歉！该时段已被预约");
        }


        PatientRegistration patientRegistration = BeanUtil.copyProperties(request, PatientRegistration.class);
        patientRegistration.setCreateTime(new Date());
        patientRegistration.setApplyDate(sf.format(new Date()));
        patientRegistration.setStatus("1");
        patientRegistration.setResumeId(request.getResumeId());
        this.baseMapper.insert(patientRegistration);
    }

    @Override
    public void deletePatientRegistration(Long id) {
        this.baseMapper.deleteById(id);
    }


    @Override
    public PatientRegistrationDto getPatientRegistrationOne(Long id) {
        LambdaQueryWrapper<PatientRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientRegistration::getId, id);
        PatientRegistration one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, PatientRegistrationDto.class);
    }

    @Override
    public List<PatientRegistrationDto> getPatientRegList() {


        UserAccountDto user = UserHolder.getUser();

        if (user == null) {
            throw new ServiceException("请先登录");
        }

        PatientRegistrationRequest request = new PatientRegistrationRequest();
        request.setId(Long.valueOf(user.getId()));

        return this.baseMapper.selectPatientRegistrationList(request);

    }

}




