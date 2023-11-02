package com.tmall.project5.conf;

import com.tmall.project5.service.UserService;
import com.tmall.project5.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tmall.project5.service"})
public class ApplicationConfig {
    @Bean
    public UserService userService(){return new UserServiceImpl();}
}
