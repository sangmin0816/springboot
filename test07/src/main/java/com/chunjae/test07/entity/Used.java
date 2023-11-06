package com.chunjae.test07.entity;

import lombok.Data;

@Data
public class Used {
    private int usedNo;
    private int productNo;
    private String userId;
    private boolean isTpay = false;
    private String addr1;
    private String addr2;
    private boolean isDiscount = false;
    private int visited = 0;
}
