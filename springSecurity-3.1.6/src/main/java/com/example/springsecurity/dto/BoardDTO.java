package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bno;
    private String title;
    private String content;
    private String author;
    @Builder.Default
    private int visited = 0;
    @Builder.Default
    private String status = "ACTIVE";
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
