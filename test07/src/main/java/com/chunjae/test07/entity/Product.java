package com.chunjae.test07.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private int productNo;
    private String tableName;
    private int categoryNo;
    private String title;
    private String content;
    private int imageFile;
    private int price;
    private Date createAt;
    private Date updateAt;
    private Date baseAt;
    private boolean isFree = false;
    private String status = "sale";
}
