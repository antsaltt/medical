package com.pokfulamroad.admintemplate.business.interceptor;

import com.pokfulamroad.admintemplate.business.utils.UserHolder;
import com.pokfulamroad.admintemplate.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
public class LoginInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = UserHolder.getUser();
        if("OPTIONS".equals(request.getMethod())) return true;

        // 检查用户是否存在
        if (user == null) {
            // 记录日志，提供更多上下文信息
            log.warn("用户未登录, 请求的URL: {}, 请求方法: {}", request.getRequestURL().toString(), request.getMethod());
            throw new ServiceException("请先登录");
            // 设置响应状态码为401，表示未授权
            //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // 清晰地将错误信息返回给客户端
            //response.getWriter().write("用户不存在");
            // 返回false，表示请求处理流程应该终止
        //    return false;
        }

        // 用户存在，继续处理请求
        return true;
    }



}
