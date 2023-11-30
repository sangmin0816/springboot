package com.shop.domain;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(
        name = "Reply", // 엔티티를 데이터베이스 테이블에 매핑할 때, 테이블의 이름
        indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")}
        // 인덱스: 데이터베이스에서 검색 속도를 빠르게 하기 위해 사용되는 데이터 구조. 특정 열의 값을 키로 사용하여 그 위치 값을 인덱스로 저장함
        // ex) name = {A, A, B, B, A} -> idx_name = [A={0, 1, 4}, B={2, 3}]
        // name: 인덱스 이름
        // columnList: 인덱스를 생성할 열
) // 쿼리 조건에 자주 사용되는 컬럼에 인덱스 지정
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
// @ToString 에서 참조하는 객체를 반드시 exclude 속성값으로 지정
// 누락시 reply 쿼리 실행과 동시에 board 테이블에서 추가 쿼리 실행 필요하여 에러 발생 (@Transaction 어노테이션으로 강제 board 쿼리 실행 가능)
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // Lazy (지연 로딩): 필요한 순간까지 데이터베이스와 연결하지 않는 방식으로 동작함
    // ↔ Eager (즉시 로딩): 해당 엔티티를 로딩할 때 같이 로딩하는 방식 (성능에 영향)
    @ManyToOne(fetch = FetchType.LAZY) // 연관관계의 fetch 속성은 반드시 Lazy 로 지정
    private Board board; // Board 타입 객체를 다대일 관계로 참조 (Board 객체의 @Id 가 FK 로 자동 지정되어 테이블 생성)

    private String replyText;

    private String replyer;

    public void changeText(String text){
        this.replyText = text;
    }

}


