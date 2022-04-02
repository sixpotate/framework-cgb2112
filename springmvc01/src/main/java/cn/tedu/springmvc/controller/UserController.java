package cn.tedu.springmvc.controller;

import cn.tedu.springmvc.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

    // http://localhost:8080/springmvc01_war_exploded/user/info.do
    @GetMapping("/info.do")
    public UserVO info() {
        UserVO userVO = new UserVO();
        userVO.setUsername("chengheng");
        userVO.setPassword("1234567890");
        userVO.setEmail("chengheng@qq.com");
        return userVO;
    }

}
