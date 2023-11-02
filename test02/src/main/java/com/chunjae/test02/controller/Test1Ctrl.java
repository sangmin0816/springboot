package com.chunjae.test02.controller;

import com.chunjae.test02.domain.Test1;
import com.chunjae.test02.persistence.Test1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test/*")
public class Test1Ctrl {
    @Autowired
    private Test1Mapper test1Mapper;

    @GetMapping("test1List")
    @ResponseBody
    public List<Test1> test1List(){
        return test1Mapper.test1List();
    }

    @GetMapping("test1List2")
    @ResponseBody
    public List<Test1> test1List2(){return test1Mapper.test1List2();}

}
