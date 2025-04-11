package com.pokfulamroad.admintemplate.common.notice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeDto;
import com.pokfulamroad.admintemplate.common.notice.entity.NoticeRequest;
import com.pokfulamroad.admintemplate.common.notice.service.NoticeService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/portal/notice","/notice"})
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody NoticeRequest request) {
        noticeService.addNotice(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<String> delete(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return CommonResult.ok();
    }

    @PostMapping("/edit")
    public CommonResult<String> updateNotice(@RequestBody NoticeRequest request) {
        noticeService.updateNotice(request);
        return CommonResult.ok();
    }

    @PostMapping("/page")
    public CommonResult<Page<NoticeDto>> page(@RequestBody NoticeRequest request) {
        Page<NoticeDto> NoticePage = noticeService.getNoticePage(request);
        return CommonResult.data(NoticePage);
    }

    @GetMapping("/lately10")
    public CommonResult<List<NoticeDto>> lately5() {
        NoticeRequest noticeRequest = new NoticeRequest();
        noticeRequest.setLimit(10);
        List<NoticeDto> lately = noticeService.getLately(noticeRequest);
        return CommonResult.data(lately);
    }

    @GetMapping("/one/{id}")
    public CommonResult<NoticeDto> saveMenu(@PathVariable Long id) {
        NoticeDto NoticeOne = noticeService.getNoticeOne(id);
        return CommonResult.data(NoticeOne);
    }
}
