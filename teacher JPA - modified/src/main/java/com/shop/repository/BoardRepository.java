package com.shop.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.shop.domain.Board;
import com.shop.repository.search.BoardSearch;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @EntityGraph(attributePaths = {"imageSet"})
    // 엔티티를 조회할 때 연관된 엔티티 그래프를 지정하여 한 번에 필요한 연관 엔티티들을 함께 조회
    // 아래 메소드를 실행할 때 연관 속성인 imageSet 을 Board 엔티티와 함께 로딩함
    @Query("select b from Board b where b.bno =:bno")
    Optional<Board> findByIdWithImages(Long bno);

}
