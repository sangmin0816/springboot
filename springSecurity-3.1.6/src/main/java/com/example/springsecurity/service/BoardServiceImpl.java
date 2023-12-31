package com.example.springsecurity.service;

import com.example.springsecurity.dto.BoardDTO;
import com.example.springsecurity.dto.BoardPostDTO;
import com.example.springsecurity.dto.PageDTO;
import com.example.springsecurity.entity.Board;
import com.example.springsecurity.entity.Post;
import com.example.springsecurity.repository.BoardRepository;
import com.example.springsecurity.repository.PostRepository;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BoardRepository boardRepo;
    @Autowired
    private PostRepository postRepo;

    @Override
    public Integer boardAdd(BoardDTO boardDTO) {
        // teacher, title 필수
        Board board = modelMapper.map(boardDTO, Board.class);
        return boardRepo.save(board).getBno();
    }

    @Override
    public Integer boardEdit(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        return boardRepo.save(board).getBno();
    }

    @Override
    public Integer boardRemove(Integer bno) {
        Optional<Board> result = boardRepo.findById(bno);
        Board board = result.orElseThrow();
        board.setStatus("REMOVE");

        postRepo.boardPostRemove(bno);

        return boardRepo.save(board).getBno();
    }

    @Override
    public void boardDelete(Integer  bno) {
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

    @Override
    public BoardPostDTO boardDetail(Integer  bno) {
        Optional<Board> result = boardRepo.findById(bno);
        Board board = result.orElseThrow();
        BoardPostDTO dto = modelMapper.map(board, BoardPostDTO.class);
        return dto;
    }
}
