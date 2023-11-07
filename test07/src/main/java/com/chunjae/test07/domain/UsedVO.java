package com.chunjae.test07.domain;

import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.UsedProduct;
import lombok.Data;

import java.util.List;

@Data
public class UsedVO {
    private UsedProduct usedProduct;
    private List<FileData> files;
    private FileData thumbnail;
}
