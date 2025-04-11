package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoDto;
import com.pokfulamroad.admintemplate.business.entity.HospitalInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.HospitalInfo;
import com.pokfulamroad.admintemplate.business.mapper.HospitalInfoMapper;
import com.pokfulamroad.admintemplate.business.service.HospitalInfoService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo>
        implements HospitalInfoService {


    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public Page<HospitalInfoDto> getHospitalInfoPage(HospitalInfoRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(10).setCurrent(request.getCurrent());
        return this.baseMapper.selectHospitalInfoPage(request, objectPage);
    }

    @Override
    public void addHospitalInfo(HospitalInfoRequest request) {
        HospitalInfo hospitalInfo = BeanUtil.copyProperties(request, HospitalInfo.class);
        hospitalInfo.setCreateTime(new Date());
        this.baseMapper.insert(hospitalInfo);
    }

    @Override
    public void deleteHospitalInfo(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateHospitalInfo(HospitalInfoRequest request) {
        HospitalInfo hospitalInfo = BeanUtil.copyProperties(request, HospitalInfo.class);
        this.baseMapper.updateById(hospitalInfo);
    }

    @Override
    public HospitalInfoDto getHospitalInfoOne(Long id) {
        LambdaQueryWrapper<HospitalInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HospitalInfo::getId, id);
        HospitalInfo one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, HospitalInfoDto.class);
    }

    /**
     * 获取医院信息列表。
     */
    @Override
    public List<HospitalInfoDto> getHospitalInfoList(HospitalInfoRequest request) {
        // 定义Redis中存储医院信息的键名
        String key = "hospital";

        // 尝试从Redis获取医院信息列表
        String hospitalList = stringRedisTemplate.opsForValue().get(key);

        // 如果Redis中存在医院信息，则将其转换为HospitalInfoDto列表后返回
        if (StrUtil.isNotEmpty(hospitalList)) {
            JSONArray objects = JSONUtil.parseArray(hospitalList);
            return objects.stream()
                    .map(x -> BeanUtil.toBean(x, HospitalInfoDto.class))
                    .toList();
        }

        // 从数据库中查询医院信息
        List<HospitalInfoDto> hospitalInfoDtos = this.baseMapper.selectHospitalInfoList(request);
        // 将查询结果存储到Redis缓存中，缓存有效期为1天
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(hospitalInfoDtos), 1, TimeUnit.DAYS);

        return hospitalInfoDtos;
    }


}




