package com.springboot.test03.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserCtrl {
    @GetMapping("userInfoList.do")
    public String userInfoList(){

        return "/user/userInfoList";
    }
}
