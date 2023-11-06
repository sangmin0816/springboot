package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.Board;
import com.chunjae.test07.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select({"<script>","SELECT * FROM board WHERE",
            "<if test='searchType != null and searchType != \"\"'> ${searchType} LIKE CONCAT('%', #{searchKeyword}, '%') AND</if>",
            "authority!='REMOVE' AND boardType=#{boardType}"+
            "ORDER BY createAt ASC LIMIT #{postStart}, #{postCount}","</script>"})
    public List<Board> boardList(Page page);
    @Select("SELECT * FROM board WHERE authority!='REMOVE' AND boardType=#{boardType}")
    public List<Board> boardTypeList(String boardType);
    @Select("SELECT * FROM board WHERE author=#{author} AND authority!='REMOVE'")
    public List<Board> boardAuthorList();
    @Select("SELECT boardNo from board ORDER BY boardNo DESC LIMIT 1")
    public int boardGetLast();

    @Select("SELECT * FROM board WHERE boardNo=#{boardNo} AND authority!='REMOVE'")
    public Board boardGet(int boardNo);
    @Insert("INSERT INTO board(boardType, title, content, author, hasFile, hasResponse, authority) VALUES(#{boardType}, #{title}, #{content}, #{author}, #{hasFile}, #{hasResponse}, #{authority})")
    public int boardInsert(Board board);
    @Update("UPDATE board SET title=#{title}, content=#{content}, hasFile=#{hasFile}, hasResponse=#{hasResponse}, authority=#{authority} WHERE boardNo=#{boardNo}")
    public int boardUpdate(Board board);
    @Update("UPDATE board SET visited=visited+1 WHERE boardNo=#{boardNo}")
    public int boardVisitUpdate(int boardNo);
    @Update("UPDATE board SET authority='REMOVE' WHERE boardNo=#{boardNo}")
    public int boardRemoveUpdate(int boardNo);
    @Delete("DELETE FROM board WHERE boardNo=#{boardNo}")
    public int boardDelete(int boardNo);
}
