package com.wenjie.blog.Interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebConif
 * @description: TODO 类描述
 * @author: Wenjie FU
 * @date: 25/03/2022
 **/
@Configuration
public class WebConif implements WebMvcConfigurer {
    /*
    * 进行 URL 访问拦截功能
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
