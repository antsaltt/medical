package com.pokfulamroad.admintemplate.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.entity.PostCommentDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.PostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PostCommentMapper extends BaseMapper<PostComment> {

        Page<PostCommentDto> selectPostCommentPage(@Param("request") PostRequest request, @Param("page") Page<Object> page);
        List<PostCommentDto> selectPostCommentList(@Param("request")PostRequest request);


}




