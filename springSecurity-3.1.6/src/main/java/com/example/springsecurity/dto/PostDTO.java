package com.example.springsecurity.dto;

import com.example.springsecurity.entity.Comment;
import com.example.springsecurity.entity.Layout;
import com.example.springsecurity.entity.PostFile;
import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private Long pno;
    private Integer bno;
    private String author;
    private String content;
    private String bgColor;
    private Long bgImage;
    private String status;
    private Boolean vote;

    private PostFile file;              // 본 포스트잇의 파일
    private Layout layout;              // 본 포스트잇의 레이아웃
    private List<Comment> comments;     // 본 포스트잇에 달린 댓글들
}
