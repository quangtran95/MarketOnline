package com.quang.spring.demo.config;

import com.quang.spring.demo.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
   @Autowired
   RequestInterceptor requestInterceptor;

   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedOrigins("http://localhost:3000")
            .allowedHeaders("Authorization", "Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers")
            .allowCredentials(true)
            .maxAge(10000);
            ;
   }

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new RequestInterceptor());
   }
}
