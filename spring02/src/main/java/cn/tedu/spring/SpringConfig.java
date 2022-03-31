package cn.tedu.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScan("cn.tedu.spring")
@ComponentScan({"cn.tedu.spring"})
// @ComponentScan(value = "cn.tedu.spring")
// @ComponentScan(value = {"cn.tedu.spring"})
public class SpringConfig {

}
