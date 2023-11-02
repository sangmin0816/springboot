package com.springboot.test03.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private int no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String tel;
    private String regdate;
    private int point;
    private boolean active = true;
    private String type = "student";
}
