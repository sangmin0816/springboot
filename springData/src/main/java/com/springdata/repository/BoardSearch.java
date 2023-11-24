package com.springdata.repository;

import com.springdata.dto.PageDTO;
import com.springdata.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchPage(Pageable pageable, PageDTO pageDTO);
}
