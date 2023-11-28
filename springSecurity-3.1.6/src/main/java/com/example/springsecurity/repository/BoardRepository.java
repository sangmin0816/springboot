package com.example.springsecurity.repository;

import com.example.springsecurity.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // 왜 책에서는 이게 없을까? 일단 추가해보자.
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @Query("select b from Board b where b.bno =:bno")
    Optional<Board> findBoardById(Long bno);

    @Query("select b from Board b")
    List<Board> findBoardAll();

/*    @Query("update Board set title =:title, content =:content where Board.bno =:bno")
    void updateBoard(@Param("title") String title, @Param("content") String content, @Param("bno") Long bno);*/

    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);
}