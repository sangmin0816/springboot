package com.tmall.project5.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String pw;
    private String name;
    private String email;
    private String tel;
    private String addr1;
    private String addr2;
    private String postcode;
    private String regdate;
    private String birth;
    private int point;
    private int imageFile;
    private String level = "student";
    private boolean active = true;
    private boolean verify = false;
}
