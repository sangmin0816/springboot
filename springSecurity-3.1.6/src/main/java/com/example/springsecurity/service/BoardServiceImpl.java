package com.example.springsecurity.service;

import com.example.springsecurity.dto.BoardDTO;
import com.example.springsecurity.dto.PageDTO;
import com.example.springsecurity.entity.Board;
import com.example.springsecurity.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class BoardServiceImpl implements BoardService{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private BoardRepository boardRepo;

    @Override
    public Long boardAdd(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        return boardRepo.save(board).getBno();
    }

    @Override
    public Long boardEdit(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        return boardRepo.save(board).getBno();
    }

    @Override
    public BoardDTO boardDetail(Long bno) {
        Optional<Board> result = boardRepo.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    @Override
    public Long boardRemove(Long bno) {
        Optional<Board> result = boardRepo.findById(bno);
        Board board = result.orElseThrow();
        board.setStatus("REMOVE");
        return boardRepo.save(board).getBno();
    }

    @Override
    public void boardDelete(Long bno) {
        boardRepo.deleteById(bno);
    }

    @Override
    public PageDTO<Board, BoardDTO> boardList(PageDTO<Board, BoardDTO> pageDTO) {
        Pageable pageable = pageDTO.getPageable();
        Page<Board> result = boardRepo.searchPage(pageable, pageDTO);
        pageDTO.build(result);
        pageDTO.entity2dto(result, BoardDTO.class);
        return pageDTO;
    }
}
