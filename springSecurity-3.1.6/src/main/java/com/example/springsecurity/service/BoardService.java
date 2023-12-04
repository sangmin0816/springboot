package com.example.springsecurity.service;

import com.example.springsecurity.dto.BoardDTO;
import com.example.springsecurity.dto.BoardPostDTO;
import com.example.springsecurity.dto.PageDTO;
import com.example.springsecurity.entity.Board;

public interface BoardService {
    public Integer boardAdd(BoardDTO boardDTO);
    public Integer boardEdit(BoardDTO boardDTO);
    public Integer boardRemove(Integer bno);

    public void boardDelete(Integer bno);

    public PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO);
    public BoardPostDTO boardDetail(Integer bno);
}
