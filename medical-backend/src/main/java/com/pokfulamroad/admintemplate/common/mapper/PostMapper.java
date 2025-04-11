package com.pokfulamroad.admintemplate.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pokfulamroad.admintemplate.common.entity.PostDto;
import com.pokfulamroad.admintemplate.common.entity.PostRequest;
import com.pokfulamroad.admintemplate.common.entity.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PostMapper extends BaseMapper<Post> {

    Page<PostDto> selectPostPage(@Param("page") Page page, @Param("request") PostRequest request);
    List<PostDto> selectPostList( @Param("request") PostRequest request);

}




