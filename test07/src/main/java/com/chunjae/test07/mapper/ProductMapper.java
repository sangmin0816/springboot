package com.chunjae.test07.mapper;

import com.chunjae.test07.entity.Product;
import com.chunjae.test07.entity.Product;
import com.chunjae.test07.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select({"<script>","SELECT * FROM product WHERE",
            "<if test='searchType != null and searchType != \"\"'> ${searchType} LIKE CONCAT('%', #{searchKeyword}, '%') AND</if>",
            "status!='REMOVE' AND tableName=#{tableName}"+
                    "ORDER BY baseAt ASC LIMIT #{postStart}, #{postCount}","</script>"})
    public List<Product> productList(Page page);

    @Select("SELECT * FROM product WHERE productNo=#{productNo} AND status!='out'")
    public Product productGet(int productNo);
    @Select("SELECT productNo FROM product ORDER BY productNo DESC LIMIT 1")
    public int productGetLast();

    @Insert("INSERT INTO product(tableName, categoryNo, title, content, imageFile, price, isFree, status) VALUES(#{tableName}, #{categoryNo}, #{title}, #{content}, #{imageFile}, #{price}, #{isFree}, #{status})")
    public int productInsert(Product product);
    @Update("UPDATE product SET title=#{title}, content=#{content}, imageFile=#{imageFile}, price=#{price}, isFree=#{isFree} WHERE productNo=#{productNo}")
    public int productUpdate(Product product);

    @Update("UPDATE product SET status=#{status} WHERE productNo=#{productNo}")
    public int productStatusUpdate(int productNo, String status);
    @Update("UPDATE product SET baseAt=CURRENT_TIMESTAMP WHERE productNo=#{productNo}")
    public int productBaseUpdate(int productNo);
    @Update("UPDATE product SET status='REMOVE' WHERE productNo=#{productNo}")
    public int productRemoveUpdate(int productNo);

    @Delete("DELETE FROM product WHERE productNo=#{productNo}")
    public int productDelete(int productNo);
}
