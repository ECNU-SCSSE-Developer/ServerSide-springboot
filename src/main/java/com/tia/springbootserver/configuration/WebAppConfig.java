package com.tia.springbootserver.configuration;

import com.tia.springbootserver.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器配置
 * @author Andrew Dong
 * @date 2018/10/20 19:20
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    /**
     * 对拦截器进行Bean处理, 否则拦截器中无法注入Bean
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    /**
     * 注册拦截器MyInterceptor
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/onLogin");
    }
}
