package com.chunjae.test07.domain;

import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Product;
import com.chunjae.test07.entity.Used;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private Product product;
    private Used used;
    private FileData thumbnail;
    private List<FileData> files;
    private int likes;
    private int buys;
}
