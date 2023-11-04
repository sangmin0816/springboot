package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private int fileNo;
    private int boardNo;
    private String originName;
    private String saveName;
    private String savePath;
    private String fileType;
    private String authority = "ALL";
}
