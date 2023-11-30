package com.shop.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageSet")
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column(length = 500, nullable = false) //컬럼의 길이와 null허용여부
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }

    // 양방향 연관관계 사용 시 각 엔티티에 해당하는 테이블을 독립적으로 생성하고 중간에 매핑해주는 매핑 테이블이 별도로 생성됨
    // @JoinColumn 또는 mappedBy 속성을 이용하면 매핑 테이블을 생성하지 않을 수 있다.
    @OneToMany(mappedBy = "board",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    // BoardImage 엔티티의 관점에서 해당 엔티티의 board 속성을 기준으로 매핑
    // Cascade(전이): 상위 엔티티 Board 가 변경되었을 때 하위 엔티티 BoardImage 도 같이 변경
    @Builder.Default
    @BatchSize(size = 20)
    // 지정된 수만큼 하위 엔티티들을 in 조건으로 조회함
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid).fileName(fileName)
                .board(this).ord(imageSet.size()).build();
        imageSet.add(boardImage);
    }
    public void clearImages() {
        imageSet.forEach(boardImage -> boardImage.changeBoard(null));
        this.imageSet.clear();
    }
}
