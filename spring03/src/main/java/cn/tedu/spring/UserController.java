package cn.tedu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Autowired
    // SubUserMapper > subUserMapper
    // UserMapper > userMapper
    private UserMapper mapper;

    public void reg() {
        System.out.println("UserController.reg() >> 控制器即将执行用户注册……");
        System.out.println(mapper.getClass().getName());
        mapper.insert();
    }

}
