package cn.tedu.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    public void reg() {
        System.out.println("UserController.reg() >> 控制器即将执行用户注册……");
        userMapper.insert();
    }

}
