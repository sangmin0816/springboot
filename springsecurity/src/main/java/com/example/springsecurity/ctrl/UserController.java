package com.example.springsecurity.ctrl;

import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping( "/")
    public String home(Model model){

        return "index";
    }

    @GetMapping( "/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(){return "index";}

    @GetMapping("/admin")
    public String admin(){return "authority/admin";}

    @GetMapping("/teacher")
    public String teacher(){return "authority/teacher";}

    @GetMapping("/student")
    public String student(){return "authority/student";}

    @GetMapping("/access-denied")
    public ModelAndView getUserPermissionExceptionPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/access-denied");
        return mv;
    }
}
