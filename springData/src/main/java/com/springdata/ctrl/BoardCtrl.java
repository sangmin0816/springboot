package com.springdata.ctrl;

import com.springdata.dto.BoardDTO;
import com.springdata.dto.PageDTO;
import com.springdata.entity.Board;
import com.springdata.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board/**")
public class BoardCtrl {
    @Autowired
    private BoardService boardService;

    @RequestMapping("boardList")
    public String boardList(Model model, HttpServletRequest request){
        PageDTO<Board, BoardDTO> pageDTO = new PageDTO<>();

        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int pageNow = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        pageDTO.setType(type);
        pageDTO.setKeyword(keyword);
        pageDTO.setPageNow(pageNow);

        pageDTO = boardService.boardList(pageDTO);
        List<BoardDTO> boardList = pageDTO.getListDTO();
        model.addAttribute("boardList", boardList);

        model.addAttribute("pageDTO", pageDTO);

        return "boardList";
    }
}
