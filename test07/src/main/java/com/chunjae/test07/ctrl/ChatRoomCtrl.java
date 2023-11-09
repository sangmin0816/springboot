package com.chunjae.test07.ctrl;

import com.chunjae.test07.biz.ChatService;
import com.chunjae.test07.domain.ChatMessage;
import com.chunjae.test07.domain.ChatRoom;
import com.chunjae.test07.biz.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/chat/")
public class ChatRoomCtrl {
    @Autowired
    private ChatService service;

    @GetMapping("home")
    public String loadHome(Model model, HttpServletRequest request){
        int usedNo = Integer.parseInt(request.getParameter("usedNo"));
        List<ChatRoom> chatRooms = service.chatRoomProductList(usedNo);

        model.addAttribute("chatRooms", chatRooms);
        return "/chat/chat";
    }

    @PostMapping("createRoom")
    @ResponseBody
    public ChatRoom createRoom(HttpServletRequest request){
        String userId = request.getParameter("userId");
        int usedNo = Integer.parseInt(request.getParameter("usedNo"));

        return service.chatRoomInsert(userId, usedNo);
    }

    @PostMapping("getRoom")
    @ResponseBody
    public List<ChatMessage> getRoom(HttpServletRequest request){
        int roomNo = Integer.parseInt(request.getParameter("roomNo"));
        String sender = request.getParameter("userId");

        return service.chatMessageList(roomNo, sender);
    }

    @PostMapping("blockRoom")
    @ResponseBody
    public String blockRoom(HttpServletRequest request){
        int roomNo = Integer.parseInt(request.getParameter("roomNo"));
        int returnNo = service.chatRoomBlockUpdate(roomNo);
        if(returnNo>0){
            return "Block Successfully";
        }

        return "Something went wrong";
    }

    @PostMapping("readRoom")
    @ResponseBody
    public String readRoom(HttpServletRequest request){
        int roomNo = Integer.parseInt(request.getParameter("roomNo"));
        String sender = request.getParameter("userId");

        int returnNo = service.chatMessageReadUpdates(roomNo, sender);
        if(returnNo>0){
            return "Success";
        }

        return "Something went wrong";
    }
}