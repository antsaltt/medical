package com.pokfulamroad.admintemplate.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pokfulamroad.admintemplate.common.entity.PostDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.Post;

import java.util.List;


public interface PostService extends IService<Post> {


    Page<PostDto> getPostPage(PostRequest request);

    void addPost(PostRequest request);

    void deletePost(PostRequest request);

    void updatePost(PostRequest request);

    PostDto getPostOne(PostRequest request);

    List<PostDto> getLately(int num);



}
