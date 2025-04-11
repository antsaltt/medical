package com.pokfulamroad.admintemplate.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoDto;
import com.pokfulamroad.admintemplate.business.entity.DepartmentInfoRequest;
import com.pokfulamroad.admintemplate.business.entity.domain.DepartmentInfo;
import com.pokfulamroad.admintemplate.business.mapper.DepartmentInfoMapper;
import com.pokfulamroad.admintemplate.business.service.DepartmentInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DepartmentInfoServiceImpl extends ServiceImpl<DepartmentInfoMapper, DepartmentInfo>
        implements DepartmentInfoService {


    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void addDepartmentInfo(DepartmentInfoRequest request) {
        DepartmentInfo departmentInfo = BeanUtil.copyProperties(request, DepartmentInfo.class);
        departmentInfo.setCreateTime(new Date());
        this.baseMapper.insert(departmentInfo);
    }

    @Override
    public void deleteDepartmentInfo(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateDepartmentInfo(DepartmentInfoRequest request) {
        DepartmentInfo departmentInfo = BeanUtil.copyProperties(request, DepartmentInfo.class);
        this.baseMapper.updateById(departmentInfo);
    }

    @Override
    public DepartmentInfoDto getDepartmentInfoOne(Long id) {
        LambdaQueryWrapper<DepartmentInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DepartmentInfo::getId, id);
        DepartmentInfo one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, DepartmentInfoDto.class);
    }

    /**
     * 获取科室信息列表。
     */
    @Override
    public List<DepartmentInfoDto> getDepartmentInfoList(DepartmentInfoRequest request) {

        // 定义Redis中存储部门信息的键名
        String key = "department";

        // 尝试从Redis获取部门信息
        String department = stringRedisTemplate.opsForValue().get(key);

        // 如果Redis中存在部门信息，则将其转换为DepartmentInfoDto列表并返回
        if (StrUtil.isNotEmpty(department)) {
            JSONArray objects = JSONUtil.parseArray(department);
            return objects.stream()
                    .map(x -> BeanUtil.toBean(x, DepartmentInfoDto.class))
                    .toList();
        }

        // 从数据库中查询部门信息
        List<DepartmentInfoDto> departmentInfoDtos = this.baseMapper.selectDepartmentInfoList(request);

        // 将查询结果存储到Redis缓存中
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(departmentInfoDtos));

        return departmentInfoDtos;

    }


}




