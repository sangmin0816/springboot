package com.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

// 게시물 목록 ( 게시물에 속한 댓글 개수)
@Data
public class BoardListReplyCountDTO {
    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private Long replyCount;        // 게시물에 속한 댓글 개수
}
