package com.example.jwtdemo.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 拦截器注册
 * @author: clx
 * @date: 2019/5/27
 * @version: 1.1.0
 */
@Configuration
public class JWTConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                // 过滤不来拦截的请求路径, 可以写在配置文件中读取
        .excludePathPatterns("/jwtTest/unToken","/jwtTest/getToken","/error","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");

    }
    @Bean
    public JWTInterceptor authenticationInterceptor() {
        return new JWTInterceptor();
    }
}