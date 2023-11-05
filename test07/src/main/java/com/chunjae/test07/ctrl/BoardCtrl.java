package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.BoardService;
import com.chunjae.test07.domain.BoardVO;
import com.chunjae.test07.entity.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board/**")
public class BoardCtrl {
    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpSession session;

    @GetMapping("noticeList")
    public String noticeList(Model model){
        List<Board> noticeList = boardService.boardTypeList("notice");
        model.addAttribute("noticeList", noticeList);
        return "board/noticeList";
    }

    @GetMapping("noticeGet")
    public String noticeGet(int boardNo, Model model){
        Board notice = boardService.boardRead(boardNo).getBoard();
        model.addAttribute("notice", notice);

        if(notice.getCreateAt().equals(notice.getUpdateAt())){
            model.addAttribute("isUpdate", false);
        } else{
            model.addAttribute("isUpdate", true);
        }

        return "board/noticeDetail";
    }

    @GetMapping("noticeWrite")
    public String noticeWrite(){
        return "board/noticeWrite";
    }

    @PostMapping("noticeWrite")
    public String noticeWrite(HttpServletRequest request){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
//        String author = (String) session.getAttribute("sid");
        String author = "admin";

        BoardVO boardvo = new BoardVO();
        Board board = new Board();
        board.setBoardType("notice");
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);
        board.setHasFile(false);
        board.setHasResponse(false);
        board.setAuthority("ALL");
        boardvo.setBoard(board);

        int success = boardService.boardWrite(boardvo);
        System.out.println(success);

        return "redirect:noticeList";
    }

    @GetMapping("noticeEdit")
    public String noticeEdit(HttpServletRequest request, Model model){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        Board board = boardService.boardGet(boardNo).getBoard();
        model.addAttribute("notice", board);
        return "board/noticeEdit";
    }

    @PostMapping("noticeEdit")
    public String noticeEdit(HttpServletRequest request){
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardVO board = boardService.boardGet(boardNo);
        board.getBoard().setTitle(title);
        board.getBoard().setContent(content);

        int success = boardService.boardEdit(board);
        System.out.println(success);

        return "redirect:noticeGet?boardNo="+boardNo;
    }
}
