package com.moyang.homework.core.config;

/**
 * @program: homework
 * @description: 添加自定义参数解析器和拦截器
 * @author: MoYang
 * @create: 2020-05-18 15:56
 **/

import com.moyang.homework.core.interceptors.AuthenticationInterceptor;
import com.moyang.homework.core.interceptors.auth.CurrentUserMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @BelongsProject:
 * @BelongsPackage: com.jdtaste.jdtastesso.conf
 * @Author:
 * @CreateTime: 2018-07-04 10:03
 * @Description: 配置URLInterceptor拦截器，以及拦截路径
 */
@EnableWebMvc
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/*/*");

    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 将/static/**访问映射到classpath:/mystatic/
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/mystatic/");
//    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    public CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    /**
     * 解决 拦截器中注入bean 失败情况出现
     * addArgumentResolvers方法中 添加
     *  argumentResolvers.add(currentUserMethodArgumentResolver());
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
