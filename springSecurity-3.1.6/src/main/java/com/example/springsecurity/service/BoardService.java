package com.example.springsecurity.service;

import com.example.springsecurity.dto.BoardDTO;
import com.example.springsecurity.dto.PageDTO;
import com.example.springsecurity.entity.Board;

public interface BoardService {
    Long boardAdd(BoardDTO boardDTO);
    Long boardEdit(BoardDTO boardDTO);
    BoardDTO boardDetail(Long bno);
    Long boardRemove(Long bno);
    void boardDelete(Long bno);
    PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO);
}
