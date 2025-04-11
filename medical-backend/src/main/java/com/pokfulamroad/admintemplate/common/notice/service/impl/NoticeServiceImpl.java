package com.pokfulamroad.admintemplate.common.notice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.common.notice.entity.Notice;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeDto;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeRequest;
import com.pokfulamroad.admintemplate.common.notice.mapper.NoticeMapper;
import com.pokfulamroad.admintemplate.common.notice.service.NoticeService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
        implements NoticeService {

    @Override
    public List<NoticeDto> getLately(NoticeRequest request) {
        return this.baseMapper.selectNoticeList(request);
    }

    @Override
    public Page<NoticeDto> getNoticePage(NoticeRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(10).setCurrent(request.getCurrent());
        return this.baseMapper.selectNoticePage(request, objectPage);
    }

    @Override
    public void addNotice(NoticeRequest request) {
        Notice Notice = BeanUtil.copyProperties(request, Notice.class);
        Notice.setCreateTime(new Date());
        this.baseMapper.insert(Notice);
    }

    @Override
    public void deleteNotice(Long id) {
        this.baseMapper.deleteById(id);
    }

    @Override
    public void updateNotice(NoticeRequest request) {
        Notice Notice = BeanUtil.copyProperties(request, Notice.class);
        this.baseMapper.updateById(Notice);
    }

    @Override
    public NoticeDto getNoticeOne(Long id) {
        LambdaQueryWrapper<Notice> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Notice::getId, id);
        Notice one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, NoticeDto.class);
    }

}




