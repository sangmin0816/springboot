package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsedProduct {
    private int usedNo;
    private int categoryNo;
    private String userId;
    private String title;
    private String content;
    private int price;
    private boolean free;
    private boolean tpay;
    private boolean discount;
    private String addr1;
    private String addr2;
    private Date createAt;
    private Date updateAt;
    private Date baseAt;
    private String status = "sale";
    private int visited = 0;
}
