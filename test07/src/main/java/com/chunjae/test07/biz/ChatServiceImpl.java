package com.chunjae.test07.biz;

import com.chunjae.test07.domain.ChatMessage;
import com.chunjae.test07.mapper.ChatMessageMapper;
import com.chunjae.test07.mapper.ChatRoomMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.chunjae.test07.domain.ChatRoom;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRoomMapper roomMapper;
    @Autowired
    private ChatMessageMapper chatMapper;

    @Override
    public List<ChatRoom> chatRoomProductList(int usedNo) {
        return roomMapper.chatRoomProductList(usedNo);
    }

    @Override
    public ChatRoom chatRoomGetNo(int roomNo) {
        return roomMapper.chatRoomGet(roomNo);
    }

    @Override
    public ChatRoom chatRoomInsert(String userId, int usedNo) {
        if(roomMapper.chatRoomGetUnique(userId, usedNo)>0){
            return null;
        }
        
        roomMapper.chatRoomInsert(userId, usedNo);
        return roomMapper.chatRoomGetId(usedNo, userId);
    }

    @Override
    public int chatRoomBlockUpdate(int roomNo) {
        return roomMapper.chatRoomBlockUpdate(roomNo);
    }

    @Override
    public List<ChatMessage> chatMessageList(int roomNo, String sender) {
        chatMapper.chatMessageReadUpdates(roomNo, sender);
        return chatMapper.chatMessageList(roomNo);
    }

    @Override
    public ChatMessage chatMessageInsert(ChatMessage chatMessage) {
        int roomNo = chatMessage.getRoomNo();
        ChatRoom room = roomMapper.chatRoomGet(roomNo);
        if(room.getStatus().equals("BLOCK")){
            return null; // 차단된 경우에는 메시지 전송하지 않음.
        }
        chatMapper.chatMessageInsert(chatMessage);
        return chatMapper.chatMessageGetLast();
    }

    @Override
    public int chatMessageReadUpdate(int chatNo, String sender) {
        return chatMapper.chatMessageReadUpdate(chatNo, sender);
    }

    @Override
    public int chatMessageReadUpdates(int roomNo, String sender) {
        return chatMapper.chatMessageReadUpdates(roomNo, sender);
    }

    @Override
    public int chatMessageRemoveUpdate(int chatNo) {
        return chatMapper.chatMessageRemoveUpdate(chatNo);
    }
}
