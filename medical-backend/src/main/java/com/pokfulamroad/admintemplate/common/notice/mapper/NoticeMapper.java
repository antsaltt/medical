package com.pokfulamroad.admintemplate.common.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.notice.entity.Notice;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeDto;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface NoticeMapper extends BaseMapper<Notice> {

    Page<NoticeDto> selectNoticePage(@Param("request") NoticeRequest request, @Param("page") Page<Object> page);

    List<NoticeDto> selectNoticeList(@Param("request") NoticeRequest request);


}




