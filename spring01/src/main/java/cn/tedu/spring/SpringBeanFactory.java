package cn.tedu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Random;

@Configuration
public class SpringBeanFactory {

    @Bean
    public Random random() {
        System.out.println("\tSpringBeanFactory.random()");
        return new Random();
    }

    @Bean
    public Date date() {
        System.out.println("\tSpringBeanFactory.date()");
        return new Date();
    }

}
