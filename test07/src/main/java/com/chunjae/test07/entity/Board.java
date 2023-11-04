package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private int boardNo;
    private String boardType;
    private String title;
    private String content;
    private String author;
    private String createAt;
    private String updateAt;
    private boolean hasFile;
    private boolean hasResponse;
    private String authority = "ALL";
    private int visited = 0;
}
