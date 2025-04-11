package com.pokfulamroad.admintemplate.common.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.common.entity.PostDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.Post;
import com.pokfulamroad.admintemplate.common.mapper.PostMapper;
import com.pokfulamroad.admintemplate.common.service.PostService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {


    @Override
    public Page<PostDto> getPostPage(PostRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(request.getPageSize()).setCurrent(request.getCurrent());
        return this.baseMapper.selectPostPage(objectPage,request);
    }

    @Override
    public void addPost(PostRequest request) {
        Post post = BeanUtil.copyProperties(request, Post.class);
        post.setCreateTime(new Date());
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            post.setUserId(loginIdAsLong);
        } catch (Exception e) {
            throw new ServiceException("请先登录",402);
        }


        this.baseMapper.insert(post);
    }

    @Override
    public void deletePost(PostRequest request) {
        this.baseMapper.deleteById(request.getId());
    }

    @Override
    public void updatePost(PostRequest request) {
        Post Post = BeanUtil.copyProperties(request, Post.class);
        this.baseMapper.updateById(Post);
    }

    @Override
    public PostDto getPostOne(PostRequest request) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getId, request.getId());
        Post one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, PostDto.class);
    }

    @Override
    public List<PostDto> getLately(int num) {
        return this.baseMapper.selectPostList(null).stream().limit(5).toList();
    }

}




