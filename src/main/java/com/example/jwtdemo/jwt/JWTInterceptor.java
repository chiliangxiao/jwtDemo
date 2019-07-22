package com.example.jwtdemo.jwt;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器获取token并验证token
 * @author: clx
 * @date: 2019/7/22
 * @version: 1.1.0
 */
public class JWTInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }

        // 从 http 请求头中取出 token
        String token = request.getHeader("Authorization");
        System.out.println(request.getRequestURI());
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        // 校验token
        JWTHelper.parserToken(token);

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
