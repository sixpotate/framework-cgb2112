package cn.tedu.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cart")
@RestController
public class CartController {

    @RequestMapping("/add.do")
    public String add(Long id) {
        id.toString();
        return "OK";
    }

}
