package com.darkdev.ecommerce.ecommerce_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandler(ResourceHandlerRegistry registry) {
        System.out.println(Paths.get("uploads").toAbsolutePath());

        String path = Paths.get("uploads").toAbsolutePath().toUri().toString();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/home/redrum/Documentos/Workspace/backend/backend-ecommerce-dark/uploads");
    }
}
