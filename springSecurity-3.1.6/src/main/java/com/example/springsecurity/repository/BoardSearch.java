package com.example.springsecurity.repository;

import com.example.springsecurity.dto.PageDTO;
import com.example.springsecurity.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchPage(Pageable pageable, PageDTO pageDTO);
}
