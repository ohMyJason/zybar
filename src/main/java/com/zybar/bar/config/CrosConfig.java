package com.zybar.bar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 刘佳昇
 * @Date 2019/9/3 23:50
 */


@Configuration
public class CrosConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("======");
        registry.addMapping("/**").allowedOrigins("http://localhost:63342").allowedHeaders("*").allowedMethods("GET", "POST", "DELETE", "PUT").allowCredentials(false).maxAge(3600);
    }
}
