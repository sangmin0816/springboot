package com.springdata.service;

import com.springdata.dto.BoardDTO;

public interface BoardService {
    Long boardAdd(BoardDTO boardDTO);
    Long boardEdit(BoardDTO boardDTO);
    BoardDTO boardDetail(Long bno);
    Long boardRemove(Long bno);
    void boardDelete(Long bno);
}
