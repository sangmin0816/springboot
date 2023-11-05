package com.chunjae.test07.biz;

import com.chunjae.test07.domain.BoardVO;
import com.chunjae.test07.entity.Board;
import com.chunjae.test07.entity.FileData;
import com.chunjae.test07.entity.Response;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BoardService {
    public List<Board> boardList();
    public List<Board> boardTypeList(String boardType);
    public BoardVO boardGet(int boardNo);
    public BoardVO boardRead(int boardNo);
    public int boardWrite(BoardVO board);
    public int boardEdit(BoardVO board);
    public int boardRemove(int boardNo);
    public int responseWrite(Response response);
    public int responseEdit(Response response);
    public int responseRemove(int responseNo);
    public int fileDataRemove(int fileNo);
    public int fileDataAuthority(String authority, int fileNo);
}
