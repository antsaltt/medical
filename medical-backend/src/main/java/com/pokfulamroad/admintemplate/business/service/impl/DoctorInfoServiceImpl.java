package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DoctorInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.UserAccountDto;
import com.pokfulamroad.admintemplate.business.entity.UserRateRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DoctorInfo;
import com.pokfulamroad.admintemplate.business.entity.domain.PatientRegistration;
import com.pokfulamroad.admintemplate.business.entity.domain.UserRate;
import com.pokfulamroad.admintemplate.business.mapper.DoctorInfoMapper;
import com.pokfulamroad.admintemplate.business.mapper.PatientRegistrationMapper;
import com.pokfulamroad.admintemplate.business.mapper.UserRateMapper;
import com.pokfulamroad.admintemplate.business.service.DoctorInfoService;
import com.pokfulamroad.admintemplate.business.utils.UserHolder;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo>
        implements DoctorInfoService {

    private final PatientRegistrationMapper patientRegistrationMapper;

    private final StringRedisTemplate stringRedisTemplate;

    private final UserRateMapper userRateMapper;

    private final DoctorInfoMapper doctorInfoMapper;


    /**
     * 获取医生信息分页数据。
     */
    @Override
    public Page<DoctorInfoDto> getDoctorInfoPage(DoctorInfoRequest request) {

        // 构造缓存键名
        String requestStr = request.toString();
        String key = "doctorList:" + requestStr;

        // 尝试从缓存中获取医生列表信息
        String doctorListStr = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotEmpty(doctorListStr)) {
            // 如果缓存中存在，解析缓存数据
            JSONObject entries = JSONUtil.parseObj(doctorListStr);
            Integer total = (Integer) entries.get("total");
            JSONArray records = entries.getJSONArray("records");

            // 将解析出的数据转换为 DoctorInfoDto 对象列表
            List<DoctorInfoDto> list = records.stream()
                    .map(x -> BeanUtil.toBean(x, DoctorInfoDto.class))
                    .toList();

            // 创建分页对象并设置数据
            Page<DoctorInfoDto> doctorInfoDtoPage = new Page<>();
            doctorInfoDtoPage.setRecords(list);
            doctorInfoDtoPage.setTotal(total);
            return doctorInfoDtoPage;
        }

        // 如果缓存中不存在，从数据库中查询并设置分页信息
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(request.getPageSize()).setCurrent(request.getCurrent());
        Page<DoctorInfoDto> doctorInfoDtoPage = this.baseMapper.selectDoctorInfoPage(request, objectPage);

        // 将查询结果存入缓存
        JSONObject entries = new JSONObject();
        entries.putOnce("total", doctorInfoDtoPage.getTotal());
        entries.putOnce("records", doctorInfoDtoPage.getRecords());

        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(entries));

        return doctorInfoDtoPage;

    }


    @Override
    public void addDoctorInfo(DoctorInfoRequest request) {
        DoctorInfo doctorInfo = BeanUtil.copyProperties(request, DoctorInfo.class);
        doctorInfo.setCreateTime(new Date());
        this.baseMapper.insert(doctorInfo);
    }

    @Override
    public void deleteDoctorInfo(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateDoctorInfo(DoctorInfoRequest request) {
        DoctorInfo doctorInfo = BeanUtil.copyProperties(request, DoctorInfo.class);
        this.baseMapper.updateById(doctorInfo);
    }

    @Override
    public Set<String> getUserRateList() {
        Set<String> doctorRate = stringRedisTemplate.opsForZSet().reverseRange("doctorRate", 0, -1);
        return doctorRate;
    }

    @Override
    public void saveUserRate(UserRateRequest userRateRequest) {

        UserAccountDto user = UserHolder.getUser();

        UserRate userRate = BeanUtil.copyProperties(userRateRequest, UserRate.class);
        userRate.setUserId(Long.parseLong(user.getId()));
        userRateMapper.insert(userRate);
    }

   // @Scheduled(cron = "0 */12 * * *")
    /**
     * 定时任务，根据医生ID计算平均评分并更新到Redis的ZSet中。
     * 使用Cron表达式控制执行周期，当前配置为每5分钟执行一次。
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void schedulePlanRate() {
        log.info("执行。。。");
        // 从数据库获取所有用户的评分信息
        List<UserRate> userRates = userRateMapper.selectList(Wrappers.emptyWrapper());

        // 根据医生ID分组，计算每位医生的平均评分
        Map<Long, Double> collect = userRates.stream()
                .collect(Collectors.groupingBy(UserRate::getDoctorId, Collectors.averagingInt(UserRate::getRate)));

        // 将计算结果添加到Redis的ZSet中，用于后续的排序和获取 top N 操作
        // 更新Redis中的评分信息
        collect.forEach((doctorId, avgRate) -> {
            stringRedisTemplate.opsForZSet().add("doctorRate", String.valueOf(doctorId), avgRate);

            DoctorInfo doctorInfo = doctorInfoMapper.selectById(doctorId);
            if (doctorInfo != null) {
                doctorInfo.setRate(avgRate.floatValue()); // 转换为 Float，如果需要
                doctorInfoMapper.updateById(doctorInfo);
            }
        });
    }

    /**
     * 获取指定医生的详细信息。
     */
    @Override
    public DoctorInfoDto getDoctorInfoOne(Long id) {

        // 尝试从Redis缓存中获取医生信息
        String doctorInfo = stringRedisTemplate.opsForValue().get("doctorInfo:" + id);
        if (StrUtil.isNotEmpty(doctorInfo)) {
            // 如果缓存中存在，则直接从缓存中返回医生信息
            return JSONUtil.toBean(doctorInfo, DoctorInfoDto.class);
        }

        // 格式化当前日期，用于查询当天的预约信息
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        // 构建查询条件，查询当天该医生的预约信息
        LambdaQueryWrapper<PatientRegistration> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PatientRegistration::getDoctorId, id);
        queryWrapper.eq(PatientRegistration::getApplyDate, sf.format(new Date()));
        List<PatientRegistration> patientRegistrations = patientRegistrationMapper.selectList(queryWrapper);
        List<String> list = patientRegistrations.stream().map(PatientRegistration::getTimeQuantum).map(String::valueOf).toList();

        DoctorInfoDto doctorInfoDto = this.baseMapper.selectDoctotOne(id);
        doctorInfoDto.setAppointmentTime(list);

        // 将医生信息（包含预约时间）存储到Redis缓存中
        stringRedisTemplate.opsForValue().set("doctorInfo:" + id, JSONUtil.toJsonStr(doctorInfoDto));

        return doctorInfoDto;
    }


}




