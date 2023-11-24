package com.springdata;

import com.springdata.dto.BoardDTO;
import com.springdata.dto.PageDTO;
import com.springdata.entity.Board;
import com.springdata.repository.BoardRepository;
import com.springdata.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;

    @Test
    public void testInsert() {
        Random random = new Random();
        // 1부터 10까지 난수로 title, content, author 설정
        for(int i=0; i<100; i++){
            BoardDTO board = BoardDTO.builder()
                    .title("title is " + (random.nextInt(10) + 1))
                    .content("content is " + (random.nextInt(10) + 1))
                    .author("user"+ (random.nextInt(10) + 1))
                    .build();

            boardService.boardAdd(board);
        }
    }

    @Test
    public void testSearch(){
        PageDTO<Board, BoardDTO> pageDTO = new PageDTO<>();
        pageDTO.setType("title, author");
        pageDTO.setKeyword("7");
        Pageable pageable = pageDTO.getPageable();
        Page<Board> result = boardRepository.searchPage(pageable, pageDTO);
        pageDTO.build(result, BoardDTO.class);
        List<BoardDTO> boardList = pageDTO.getListDTO();
        boardList.forEach(b-> System.out.println("SEARCH RESULT: "+b));
    }
}
