package com.miao.robot.tuling.config;

import com.miao.robot.tuling.annotation.RequestHeaderAndBodyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private RequestHeaderAndBodyResolver requestHeaderAndBodyResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestHeaderAndBodyResolver);
    }
}
