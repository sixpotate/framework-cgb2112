package cn.tedu.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class SpringBeanFactory {

    @Bean
    public Random random() {
        return new Random();
    }

}
