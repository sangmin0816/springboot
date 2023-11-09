package com.chunjae.test07.biz;

import com.chunjae.test07.domain.ChatMessage;
import com.chunjae.test07.domain.ChatRoom;

import java.util.List;

public interface ChatService {
    public List<ChatRoom> chatRoomProductList(int usedNo);
    public ChatRoom chatRoomGetNo(int roomNo);
    public ChatRoom chatRoomInsert(String userId, int usedNo);
    public int chatRoomBlockUpdate(int roomNo);

    public List<ChatMessage> chatMessageList(int roomNo, String sender);
    public ChatMessage chatMessageInsert(ChatMessage chatMessage);
    public int chatMessageReadUpdate(int chatNo, String sender);
    public int chatMessageReadUpdates(int roomNo, String sender);
    public int chatMessageRemoveUpdate(int chatNo);
}
