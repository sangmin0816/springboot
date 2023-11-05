package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int responseNo;
    private String author;
    private String content;
    private Date createAt;
    private Date updateAt;
    private String authority;
    private int boardNo;
    private int root;
    private int depth;
}
