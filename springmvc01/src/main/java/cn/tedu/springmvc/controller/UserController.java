package cn.tedu.springmvc.controller;

import org.springframework.stereotype.Controller;

@Controller // 必须是@Controller，不可以是其它组件注解
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

}
