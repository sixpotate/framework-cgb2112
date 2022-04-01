package cn.tedu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("cn.tedu.spring")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
}
