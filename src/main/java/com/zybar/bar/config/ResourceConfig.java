package com.zybar.bar.config;

/**
 * @author 刘佳昇
 * @Date 2019/8/24 3:21
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Value("${img.path}")
    public String imgPath;

    @Value("${pdf.path}")
    public String pdfPath;

    @Value("${mp3.path}")
    public String mp3Path;

    @Value("${chatImg.path}")
    public String chatImgPath;

    @Value("${userImg.path}")
    public String userImgPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/img/**").addResourceLocations("file:"+imgPath);
        registry.addResourceHandler("/file/pdf/**").addResourceLocations("file:"+pdfPath);
        registry.addResourceHandler("/file/mp3/**").addResourceLocations("file:"+mp3Path);
        registry.addResourceHandler("/file/chatImg/**").addResourceLocations("file:"+chatImgPath);
        registry.addResourceHandler("/file/userImg/**").addResourceLocations("file:"+userImgPath);
    }
}
