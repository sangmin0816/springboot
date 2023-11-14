package com.shop.per;

import com.shop.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchOne(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
