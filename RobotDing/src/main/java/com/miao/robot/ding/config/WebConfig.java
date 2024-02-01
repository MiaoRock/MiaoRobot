package com.miao.robot.ding.config;

import com.miao.robot.ding.annotation.RequestHeaderAndBodyResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer
//    extends WebMvcConfigurationSupport
{
    @Autowired
    private RequestHeaderAndBodyResolver requestHeaderAndBodyResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(requestHeaderAndBodyResolver);
    }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")
//                .addResourceLocations("classpath:/templates/");
//        super.addResourceHandlers(registry);
//    }
}
