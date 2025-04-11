package com.pokfulamroad.admintemplate.common.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.common.entity.PostCommentDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PostComment;

public interface PostCommentService extends IService<PostComment> {


    Page<PostCommentDto> getPostCommentPage(PostRequest request);

    void addPostComment(PostRequest request);

    void deletePostComment(PostRequest request);

    void updatePostComment(PostRequest request);

    PostCommentDto getPostCommentOne(PostRequest request);


}
