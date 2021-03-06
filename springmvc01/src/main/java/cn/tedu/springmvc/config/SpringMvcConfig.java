package cn.tedu.springmvc.config;

import cn.tedu.springmvc.interceptor.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 此注解不是必须的
@EnableWebMvc
@ComponentScan("cn.tedu.springmvc") // 必须配置在当前配置类，不可配置在Spring的配置类
public class SpringMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginInterceptor())
               .addPathPatterns("/user/login.do");

    }
}
