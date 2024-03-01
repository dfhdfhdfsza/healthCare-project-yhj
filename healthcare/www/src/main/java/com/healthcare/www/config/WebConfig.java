package com.healthcare.www.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/productFile/**")
                .addResourceLocations("file:///C:/health_upload/product/");

        registry.addResourceHandler("/userFile/**")
            .addResourceLocations("file:///C:/health_upload/user/");

        registry.addResourceHandler("/communityFile/**")
            .addResourceLocations("file:///C:/health_upload/community/");
    }

}
