package com.chunjae.test07.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String userId;
    private String name;
    private String pw;
    private String tel;
    private String email;
    private Date createAt;
    private int active;
}
