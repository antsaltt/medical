package com.pokfulamroad.admintemplate.common.notice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.common.notice.entity.Notice;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeDto;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeRequest;

import java.util.List;


public interface NoticeService extends IService<Notice> {

    Page<NoticeDto> getNoticePage(NoticeRequest request);

    List<NoticeDto> getLately(NoticeRequest request);

    void addNotice(NoticeRequest request);

    void deleteNotice(Long id);

    void updateNotice(NoticeRequest request);

    NoticeDto getNoticeOne(Long id);


}
