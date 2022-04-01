package cn.tedu.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // 必须是@Controller，不可以是其它组件注解
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

    // http://localhost:8080/springmvc01_war_exploded/login.do
    @RequestMapping("/login.do")
    @ResponseBody
    public String login() {
        return "UserController.login()";
    }

}
