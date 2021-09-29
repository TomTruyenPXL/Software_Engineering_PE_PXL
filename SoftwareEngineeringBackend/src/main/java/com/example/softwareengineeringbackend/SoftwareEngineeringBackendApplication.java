package com.example.softwareengineeringbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SoftwareEngineeringBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareEngineeringBackendApplication.class, args);
    }

    @Configuration
    static class WebMvcConfiguration implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {registry.addMapping("/**");}
    }
}
