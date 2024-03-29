package com.nirvana.habits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MovieServiceHandlerConfig implements WebMvcConfigurer {

    @Autowired
    private MovieServiceHandlerInterceptor movieServiceHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(movieServiceHandlerInterceptor);
    }
}
