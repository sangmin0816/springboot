package com.chunjae.test07.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    private int roomNo;
    private String userId;
    private int usedNo;
    private String status = "ON";
}
