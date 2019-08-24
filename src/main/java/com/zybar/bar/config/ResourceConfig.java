package com.zybar.bar.config;

/**
 * @author 刘佳昇
 * @Date 2019/8/24 3:21
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/img/**").addResourceLocations("file:D:/working/bar/src/main/resources/static/file/img/");
        registry.addResourceHandler("/file/pdf/**").addResourceLocations("file:D:/working/bar/src/main/resources/static/file/pdf/");
        registry.addResourceHandler("/file/mp3/**").addResourceLocations("file:D:/working/bar/src/main/resources/static/file/mp3/");
    }
}
