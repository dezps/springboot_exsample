package com.d.dezps.demo.config;

import com.d.dezps.demo.handler.LoginHandler;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyMvcConfig implements WebMvcConfigurer
{
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //springboot已经做好了静态资源映射,不用处理静态资源
        //几天后失效,需要添加避免静态资源被拦截
        registry.addInterceptor(new LoginHandler()).addPathPatterns("/**")
                //这里的路径需要加上"/"
                .excludePathPatterns("/index","/","/login","/asserts/**");
    }
}
