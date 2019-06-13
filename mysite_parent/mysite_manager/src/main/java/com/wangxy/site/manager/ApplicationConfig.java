package com.wangxy.site.manager;

import com.wangxy.site.manager.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 拦截器配置
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport{

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");

    }

    /*跨域全局配置*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

}
