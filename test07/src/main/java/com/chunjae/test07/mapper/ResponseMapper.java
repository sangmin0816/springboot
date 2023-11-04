package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.Response;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ResponseMapper {
    @Select("SELECT * FROM response WHERE boardNo=#{boardNo} AND authority!='REMOVE'")
    public List<Response> responseBoardList(int boardNo);
    @Select("SELECT * FROM response WHERE author=#{author} AND authority!='REMOVE'")
    public List<Response> responseAuthorList(String author);
    @Select("SELECT COUNT(*) FROM response WHERE boradNo=#{boardNo} AND authority!='REMOVE'")
    public int responseBoardCount(int boardNo);
    @Insert("INSERT INTO response(author, content, authority, boardNo, root, depth) VALUES(#{author}, #{content}, #{authority}, #{boardNo}, #{root}, #{depth})")
    public int responseInsert(Response response);
    @Update("UPDATE response SET content=#{content}, authority=#{authority} WHERE responseNo=#{responseNo}")
    public int responseUpdate(Response response);

    @Update("UPDATE response SET authority='REMOVE' WHERE responseNo=#{responseNo}")
    public int responseRemoveUpdate(int removeNo);

    @Update("UPDATE response SET authority='REMOVE' WHERE boardNo=#{boardNo}")
    public int responseBoardRemoveUpdate(int boradNo);

    @Delete("DELETE FROM respone WHERE responseNo=#{responseNo}")
    public int responseDelete(int responeNo);
}
