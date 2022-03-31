package cn.tedu.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class UserMapper {

    public UserMapper() {
        System.out.println("\tUserMapper.UserMapper()");
    }

    @PostConstruct
    public void init() {
        System.out.println("\tUserMapper.init()");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("\tUserMapper.destroy()");
    }

}
