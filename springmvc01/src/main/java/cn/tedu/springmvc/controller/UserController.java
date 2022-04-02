package cn.tedu.springmvc.controller;

import cn.tedu.springmvc.dto.UserRegDTO;
import cn.tedu.springmvc.vo.UserVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

    // http://localhost:8080/springmvc01_war_exploded/user/login.do?username=root&password=123456
    @RequestMapping("/login.do")
    public String login(String username, String password) {
        System.out.println("username = " + username + ", password = " + password);
        return "OK";
    }

    // http://localhost:8080/springmvc01_war_exploded/user/reg.do?username=root&password=123456&age=25
    @RequestMapping("/reg.do")
    public String reg(UserRegDTO userRegDTO) {
        System.out.println(userRegDTO);
        return "OK";
    }

    // http://localhost:8080/springmvc01_war_exploded/user/3/info.do
    @GetMapping("/{id}/info.do")
    public UserVO info(@PathVariable Long id) {
        System.out.println("即将查询 id = " + id + " 的用户的信息……");
        UserVO userVO = new UserVO();
        userVO.setUsername("chengheng");
        userVO.setPassword("1234567890");
        userVO.setEmail("chengheng@qq.com");
        return userVO;
    }

}
