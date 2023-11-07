package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.UsedProduct;
import com.chunjae.test07.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsedProductMapper {
    @Select({"<script>","SELECT * FROM usedProduct WHERE",
            "<if test='searchType != null and searchType != \"\"'> ${searchType} LIKE CONCAT('%', #{searchKeyword}, '%') AND</if>",
            "status!='REMOVE'"+
                    "ORDER BY baseAt DESC LIMIT #{postStart}, #{postCount}","</script>"})
    public List<UsedProduct> usedProductList(Page page);

    @Select("SELECT * FROM usedProduct WHERE usedNo=#{usedNo} AND status!='REMOVE'")
    public UsedProduct usedProductGet(int usedNo);
    @Select("SELECT usedNo FROM usedProduct ORDER BY usedNo DESC LIMIT 1")
    public int usedProductGetLast();

    @Insert("INSERT INTO usedProduct(categoryNo, userId, title, content, price, free, tpay, discount, addr1, addr2) VALUES(#{categoryNo}, #{userId}, #{title}, #{content}, #{price}, #{free}, #{tpay}, #{discount}, #{addr1}, #{addr2})")
    public int usedProductInsert(UsedProduct usedProduct);
    @Update("UPDATE usedProduct SET categoryNo=#{categoryNo}, title=#{title}, content=#{content}, price=#{price}, free=#{free}, tpay=#{tpay}, discount=#{discount}, addr1=#{addr1}, addr2=#{addr2}, status=#{status} WHERE usedNo=#{usedNo}")
    public int usedProductUpdate(UsedProduct usedProduct);

    @Update("UPDATE usedProduct SET status=#{status} WHERE usedNo=#{productNo}")
    public int usedProductStatusUpdate(int usedNo, String status);
    @Update("UPDATE usedProduct SET baseAt=CURRENT_TIMESTAMP WHERE usedNo=#{productNo}")
    public int usedProductBaseUpdate(int usedNo);
    @Update("UPDATE usedProduct SET status='REMOVE' WHERE usedNo=#{productNo}")
    public int usedProductRemoveUpdate(int usedNo);

    @Delete("DELETE FROM usedProduct WHERE usedNo=#{productNo}")
    public int usedProductDelete(int usedNo);
}
