package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private int responseNo;
    private String author;
    private String content;
    private String createAt;
    private String updateAt;
    private String authority;
    private int boardNo;
    private int root;
    private int depth;
}
