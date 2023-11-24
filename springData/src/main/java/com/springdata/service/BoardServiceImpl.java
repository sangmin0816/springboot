package com.springdata.service;

import com.springdata.dto.BoardDTO;
import com.springdata.entity.Board;
import com.springdata.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
}
