package com.kdt.goohae.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CertificationInterceptor())
                .addPathPatterns("/adm*")
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin-loginf")
                .excludePathPatterns("/admin-login");

        registry.addInterceptor(new UserCertificationInterceptor())
                .addPathPatterns("/logined-user/**");
    }
}
