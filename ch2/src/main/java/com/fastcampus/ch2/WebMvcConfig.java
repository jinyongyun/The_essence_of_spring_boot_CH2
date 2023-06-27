package com.fastcampus.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor()) // interceptor 등록
                .addPathPatterns("/**")                        // interceptor가 적용될 대상
                .excludePathPatterns("/css/**", "/js/**");// interceptor가 제외될 대상
    }
}
