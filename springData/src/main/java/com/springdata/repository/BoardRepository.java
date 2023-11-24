package com.springdata.repository;

import com.springdata.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 왜 책에서는 이게 없을까? 일단 추가해보자.
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}