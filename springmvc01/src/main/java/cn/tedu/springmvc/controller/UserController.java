package cn.tedu.springmvc.controller;

import cn.tedu.springmvc.dto.UserRegDTO;
import cn.tedu.springmvc.util.JsonResult;
import cn.tedu.springmvc.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import static cn.tedu.springmvc.util.JsonResult.State.ERR_PASSWORD;
import static cn.tedu.springmvc.util.JsonResult.State.ERR_USERNAME;

@RestController
@RequestMapping("/user")
public class UserController {

    public UserController() {
        System.out.println("UserController.UserController()");
    }

    // http://localhost:8080/springmvc01_war_exploded/user/login.do?username=root&password=123456
    @RequestMapping("/login.do")
    public JsonResult<Void> login(String username, String password) {
        System.out.println("username = " + username + ", password = " + password);
        // 假设 admin / 888888 是正确的 用户名 / 密码
        // 判断用户名
        if ("admin".equals(username)) {
            // 用户名正常，判断密码
            if ("888888".equals(password)) {
                // 密码也正确，登录成功
                return JsonResult.ok();
            } else {
                // 密码错误
                String message = "登录失败，密码错误！";
                return JsonResult.fail(ERR_PASSWORD, message);
            }
        } else {
            // 用户名不存在
            String message = "登录失败，用户名不存在！";
            return JsonResult.fail(ERR_USERNAME, message);
        }
    }

    // http://localhost:8080/springmvc01_war_exploded/user/reg.do?username=root&password=123456&age=25
    @RequestMapping("/reg.do")
    public JsonResult<Void> reg(UserRegDTO userRegDTO) {
        System.out.println(userRegDTO);
        return JsonResult.ok();
    }

    // http://localhost:8080/springmvc01_war_exploded/user/3/info.do
    @GetMapping("/{id:[0-9]+}/info.do")
    public JsonResult<UserVO> info(@PathVariable Long id) {
        System.out.println("即将查询 id = " + id + " 的用户的信息……");
        UserVO userVO = new UserVO();
        userVO.setUsername("chengheng");
        userVO.setPassword("1234567890");
        userVO.setEmail("chengheng@qq.com");

        return JsonResult.ok(userVO);
    }

    // http://localhost:8080/springmvc01_war_exploded/user/liucs/info.do
    @GetMapping("/{username:[a-zA-Z]+}/info.do")
    public UserVO info(@PathVariable String username) {
        System.out.println("即将查询 用户名 = " + username + " 的用户的信息……");
        UserVO userVO = new UserVO();
        userVO.setUsername("chengheng");
        userVO.setPassword("1234567890");
        userVO.setEmail("chengheng@qq.com");
        return userVO;
    }

    // http://localhost:8080/springmvc01_war_exploded/user/list/info.do
    @GetMapping("/list/info.do")
    public UserVO list() {
        System.out.println("即将查询 用户的列表 的信息……");
        return null;
    }

}
