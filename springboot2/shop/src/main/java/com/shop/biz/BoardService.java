package com.shop.biz;

import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResponseDTO;

public interface BoardService {
    public Long register(BoardDTO boardDTO);
    public BoardDTO selectOne(Long seq);
    public void modify(BoardDTO boardDTO);
    public void remove(Long seq);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}