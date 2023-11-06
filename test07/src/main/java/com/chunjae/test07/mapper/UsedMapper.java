package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.Used;
import com.chunjae.test07.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsedMapper {
    @Select("SELECT * FROM used WHERE productNo=#{productNo}")
    public Used usedGet(int productNo);
    @Select("SELECT usedNo FROM used ORDER BY usedNo DESC LIMIT 1")
    public int usedGetLast();
    @Insert("INSERT INTO used(productNo, userId, isTpay, addr1, addr2, isDiscount) VALUES(#{productNo}, #{userId}, #{isTpay}, #{addr1}, #{addr2}, #{isDiscount})")
    public int usedInsert(Used used);
    @Update("UPDATE used SET isTpay=#{isTpay}, addr1=#{addr1}, addr2=#{addr2}, isDiscount=#{isDiscount} WHERE usedNo=#{usedNo}")
    public int usedUpdate(Used used);

    @Update("UPDATE used SET visited=visited+1 WHERE usedNo=#{usedNo}")
    public int usedVisitUpdate(int usedNo);

    @Delete("DELETE FROM used WHERE usedNo=#{usedNo}")
    public int usedDelete(int usedNo);
}
