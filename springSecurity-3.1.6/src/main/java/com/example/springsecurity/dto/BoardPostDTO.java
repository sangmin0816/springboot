package com.example.springsecurity.dto;

import com.example.springsecurity.entity.BoardFile;
import com.example.springsecurity.entity.BoardGroup;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardPostDTO {
    private Integer bno;
    private String teacher;
    private String title;
    private String bpw;
    private Integer maxStudent;
    private String bgColor;
    private Integer bgImage;
    private String status = "ACTIVE";
    private String layout = "GRID";
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    private BoardFile file;             // 본 게시판의 이미지 파일
    private List<BoardGroup> groups;         // 본 게시판의 그룹
    private List<PostDTO> posts;        // 본 게시판의 포스트잇들
}
