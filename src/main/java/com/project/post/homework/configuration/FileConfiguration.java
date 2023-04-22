package com.project.post.homework.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class FileConfiguration implements WebMvcConfigurer {
    //http:localhost:8080/imgae/wecewuye.jpg
    @Value("${server.path}")
    String serverPath;
    @Value("${client.path}")
    String clientPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(clientPath)
                .addResourceLocations("file:"+serverPath);
//        WebMvcConfigurer.super.addResourceHandlers(registry);

    }
}

