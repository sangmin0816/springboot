package com.chunjae.test07.domain;

import com.chunjae.test07.entity.Board;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BoardVO {
    private Board board;
    private List<FileData> files;
    private List<Response> responses;
}
