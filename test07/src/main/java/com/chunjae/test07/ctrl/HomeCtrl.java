package com.chunjae.test07.ctrl;

import com.chunjae.test07.entity.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeCtrl {

    @GetMapping("/")
    public String home(Model model){
        Human human = new Human();
        human.setName("김일");
        human.setAge(11);
        model.addAttribute("human", human);

        List<Human> humanList = new ArrayList<>();
        humanList.add(human);
        humanList.add(new Human("김이", 22));

        model.addAttribute("attrName", "😒");
        model.addAttribute("humanList", humanList);
        return "index";
    }
}
