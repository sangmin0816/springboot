package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.FileData;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileDataMapper {
    @Select("SELECT * FROM fileData WHERE boardNo=#{boardNo} AND authority!='REMOVE'")
    public List<FileData> fileDataBoardList(int boardNo);

    @Insert("INSERT INTO fileData (boardNo, originName, saveName, savePath, fileType) VALUES (#{boardNo}, #{originName}, #{saveName}, #{savePath}, #{fileType}, #{authority})")
    public int fileDataInsert(FileData fileData);
    @Update("UPDATE fileData SET authority=#{authority} WHERE fileNo=#{fileNo}")
    public int fileDataAuthorityUpdate(String authority, int fileNo);
    @Update("UPDATE fileData SET authority='REMOVE' WHERE fileNo=#{fileNo}")
    public int fileDataRemoveUpdate(int fileNo);
    @Update("UPDATE fileData SET authority='REMOVE' WHERE boardNo=#{boardNo}")
    public int fileDataBoardRemoveUpdate(int boardNo);
    @Delete("DELETE FROM fileData WHERE fileNo=#{fileNo}")
    public int fileDataDelete(int fileNo);
}
