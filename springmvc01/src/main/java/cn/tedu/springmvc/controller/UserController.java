package cn.tedu.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

    // http://localhost:8080/springmvc01_war_exploded/user/login.do
    @RequestMapping(value = "/login.do",
            method = {RequestMethod.POST, RequestMethod.GET})
    public String login() {
        String username = "xx";
        String password = "xx";
        String email = "xx";
        return "{" +
                "\"username\": \"" +username+ "\"," +
                "\"password\": \"" +password+ "\"," +
                "\"email\": \"" +email+ "\"" +
                "}";
    }

}
