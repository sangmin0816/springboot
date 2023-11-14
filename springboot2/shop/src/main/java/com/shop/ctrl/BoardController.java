package com.shop.ctrl;

import com.shop.biz.BoardService;
import com.shop.domain.Board;
import com.shop.dto.BoardDTO;
import com.shop.dto.PageRequestDTO;
import com.shop.dto.PageResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "board/list";
    }

    @GetMapping("/register")
    public String register(Board board, Model model){
        model.addAttribute("board", board);
        return "board/register";
    }
}
