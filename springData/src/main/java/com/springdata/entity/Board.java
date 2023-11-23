package com.springdata.entity;

import javax.persistence.*;

@Entity
public class Board {

    @Id // 반드시 해당 엔티티의 PK(기본키)가 있어야 함
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 컬럼의 값 생성 전략. 이 경우 MariaDB의 AUTO_INCREMENT
    private Long bno;
    @Column(nullable = false) // null 비허용
    private String title;
    private String content;
    private String author;
    private int visited = 0; // 기본값 설정

    /*
    * 초기값 설정하는 방법
    * 1. private in visited = 0;
    * 2. @Column(columnDefinition = "int default 0")
    * 3. 생성자 사용. public Board() { this.visited = 0; }
    * */
}