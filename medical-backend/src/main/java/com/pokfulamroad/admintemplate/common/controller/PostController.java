package com.pokfulamroad.admintemplate.common.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.entity.PostCommentDto;
import com.pokfulamroad.admintemplate.common.entity.PostDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.service.PostCommentService;
import com.pokfulamroad.admintemplate.common.service.PostService;
import com.pokfulamroad.admintemplate.system.entity.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
@CrossOrigin
public class PostController {


    private final PostService postService;

    private final PostCommentService postCommentService;

    @RequestMapping("/page")
    public CommonResult<Page<PostDto>> page(@RequestBody PostRequest request) {
        Page<PostDto> postPage = postService.getPostPage(request);
        return CommonResult.data(postPage);
    }

    @RequestMapping("/save")
    public CommonResult<Void> save(@RequestBody PostRequest request) {
        postService.addPost(request);
        return CommonResult.ok();
    }

    @GetMapping("/delete/{id}")
    public CommonResult<Void> delete(@PathVariable Long id) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(id);
        postService.deletePost(postRequest);
        return CommonResult.ok();
    }

    @RequestMapping("/update")
    public CommonResult<Void> update(@RequestBody PostRequest request) {
        postService.updatePost(request);
        return CommonResult.ok();
    }

    @GetMapping("/one/{id}")
    public CommonResult<PostDto> one(@PathVariable Long id) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(id);
        PostDto one = postService.getPostOne(postRequest);
        return CommonResult.data(one);
    }

    @GetMapping("/lately6")
    public CommonResult<List<PostDto>> lately6() {
        List<PostDto> lately = postService.getLately(6);
        return CommonResult.data(lately);
    }


    @RequestMapping("/comment/page")
    public CommonResult<Page<PostCommentDto>> commentPage(@RequestBody PostRequest request) {
        Page<PostCommentDto> page = postCommentService.getPostCommentPage(request);
        return CommonResult.data(page);
    }

    @RequestMapping("/comment/save")
    public CommonResult<Void> commentSave(@RequestBody PostRequest request) {
        postCommentService.addPostComment(request);
        return CommonResult.ok();
    }

    @GetMapping("comment/delete/{id}")
    public CommonResult<Void> commentDelete(@PathVariable Long id) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(id);
        postCommentService.deletePostComment(postRequest);
        return CommonResult.ok();
    }

    @RequestMapping("comment/update")
    public CommonResult<Void> commentUpdate(@RequestBody PostRequest request) {
        postCommentService.updatePostComment(request);
        return CommonResult.ok();
    }

    @GetMapping("/comment/one/{id}")
    public CommonResult<PostCommentDto> commentOne(@PathVariable Long id) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(id);
        PostCommentDto one = postCommentService.getPostCommentOne(postRequest);
        return CommonResult.data(one);
    }


}
