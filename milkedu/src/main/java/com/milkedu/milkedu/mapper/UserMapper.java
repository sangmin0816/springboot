package com.milkedu.milkedu.mapper;

import com.milkedu.milkedu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    public List<User> userList();
    @Select("SELECT * FROM user WHERE username=#{username}")
    public User userGet(String username);
    @Insert("INSERT INTO user (authorities, username, password) VALUES (#{authoritis}, #{username}, #{password})")
    public int userInsert(User user);
}
