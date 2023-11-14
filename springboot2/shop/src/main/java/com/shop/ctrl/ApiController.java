package com.shop.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/api1")
    public String api1(){
        return "Hello~!";
    }

    @GetMapping("/api2")
    public String[] api2(){
        String[] strArr = {"kim","lee","park"};
        return strArr;
    }
}
