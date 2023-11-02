package com.tmall.project5.mapper;

import com.tmall.project5.entity.User;
import com.tmall.project5.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id NOT IN ('admin') ORDER BY regdate ASC LIMIT #{postStart}, #{postCount}")
    public List<User> userList(Page page);
    @Select("SELECT * FROM user WHERE id NOT IN ('admin') AND ${searchType} LIKE concat('%', #{searchKeyword}, '%') ORDER BY regdate ASC LIMIT #{postStart}, #{postCount}")
    public List<User> userSearchList(Page page);

    @Select("SELECT COUNT(*) FROM user WHERE id NOT IN ('admin') ORDER BY regdate ASC LIMIT #{postStart}, #{postCount}")
    public int userCount(Page page);
    @Select("SELECT COUNT(*) FROM user WHERE id NOT IN ('admin') AND ${searchType} LIKE concat('%', #{searchKeyword}, '%') ORDER BY regdate ASC LIMIT #{postStart}, #{postCount}")
    public int userSearchCount(Page page);

    @Select("SELECT * FROM user WHERE id=#{id}")
    public User userGet(String id);
    @Select("SELECT * FROM user WHERE name=#{name} AND tel=#{tel}")
    public User userFindByTel(String name, String tel);
    @Select("SELECT * FROM user WHERE name=#{name} AND email=#{email}")
    public User userFindByEmail(String name, String email);

    @Select("SELECT COUNT(*) FROM user WHERE id=#{id}")
    public int userIdCheck(String id);

    @Insert("INSERT INTO user(id, pw, name, email, tel, addr1, addr2, postcode, birth, imageFile, level) VALUES(#{id}, #{pw}, #{name}, #{email}, #{tel}, #{addr1}, #{addr2}, #{postcode}, #{birth}, #{imageFile}, #{level})")
    public int userInsert(User user);

    @Update("UPDATE user SET pw=#{pw}, name=#{name}, email=#{email}, tel=#{tel}, addr1=#{addr1}, addr2=#{addr2}, postcode=#{postcode}, birth=#{birth} WHERE id=#{id}")
    public int userUpdate(User user);
    @Update("UPDATE user SET point=#{point} WHERE id=#{id}")
    public int userPointUpdate(String id, int point);
    @Update("UPDATE user SET verify=#{verify} WHERE id=#{id}")
    public int userVerifyUpdate(String id, boolean verify);
    @Update("UPDATE user SET active=#{active} WHERE id=#{id}")
    public int userActiveUpdate(String id, boolean active);

    @Delete("DELETE FROM user WHERE id=#{id}")
    public int userDelete(String id);
}