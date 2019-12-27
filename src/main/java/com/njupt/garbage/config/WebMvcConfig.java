package com.njupt.garbage.config;

import com.njupt.garbage.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**")
                .addResourceLocations("file:/Users/wyx/IdeaProjects/garbage/src/main/resources/pictures/");
    }

    @Bean
    public LoginInterceptor loginInteceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInteceptor()).addPathPatterns("/item/**","/cat/**","/label/**","/pic/**","/user/setPermission", "/user/list");
        super.addInterceptors(registry);
    }
}
