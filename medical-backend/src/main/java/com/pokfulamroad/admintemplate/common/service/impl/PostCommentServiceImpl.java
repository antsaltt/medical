package com.pokfulamroad.admintemplate.common.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pokfulamroad.admintemplate.common.entity.PostCommentDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PostComment;
import com.pokfulamroad.admintemplate.common.mapper.PostCommentMapper;
import com.pokfulamroad.admintemplate.common.service.PostCommentService;
import com.pokfulamroad.admintemplate.system.entity.CommonPageRequest;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
    implements PostCommentService {


    @Override
    public Page<PostCommentDto> getPostCommentPage(PostRequest request) {
        Page<Object> objectPage = CommonPageRequest.defaultPage().setSize(8).setCurrent(request.getCurrent());
        return this.baseMapper.selectPostCommentPage(request, objectPage);
    }

    @Override
    public void addPostComment(PostRequest request) {
        PostComment postComment = BeanUtil.copyProperties(request, PostComment.class);
        postComment.setCreateTime(new Date());
        try {
            long loginIdAsLong = StpUtil.getLoginIdAsLong();
            postComment.setUserId(loginIdAsLong);
        } catch (Exception e) {
            throw new ServiceException("请先登录",402);
        }


        this.baseMapper.insert(postComment);
    }

    @Override
    public void deletePostComment(PostRequest request) {
        this.baseMapper.deleteById(request.getId());
    }

    @Override
    public void updatePostComment(PostRequest request) {
        PostComment PostComment = BeanUtil.copyProperties(request, PostComment.class);
        this.baseMapper.updateById(PostComment);
    }



    @Override
    public PostCommentDto getPostCommentOne(PostRequest request) {
        LambdaQueryWrapper<PostComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PostComment::getId, request.getId());
        PostComment one = this.baseMapper.selectOne(queryWrapper);
        return BeanUtil.copyProperties(one, PostCommentDto.class);
    }

}




