package com.tmall.project5.ctrl;

import com.tmall.project5.entity.User;
import com.tmall.project5.exception.NoSuchDataException;
import com.tmall.project5.service.UserService;
import com.tmall.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin("http://localhost:8085")
public class UserCtrl {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/admin/userList")
    public String getUserList(HttpServletRequest request, Model model){
        Page page = Page.pageStart(request, model);
        int total = userService.userCount(page);
        page = Page.pageEnd(request, model, page, total);

        List<User> userList = userService.userList(page);
        model.addAttribute("userList", userList);
        return "admin/user/userList";
    }

    @GetMapping("/admin/userGet")
    public String getUser(@RequestParam("id") String id, HttpSession session, Model model){
        User user = userService.userGet(id);
        if(user==null){
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return "admin/user/userGet";
    }

    @GetMapping("/login")
    public String login(){return "login";}

    @GetMapping("/auth")
    public String auth(@RequestParam("id") String id, @RequestParam("pw") String pw){
        boolean success = userService.userLogin(id, pw);
        if(success){
            session.setAttribute("sid", id);
            return "index";
        }

        return "redirect: login";
    }
}
