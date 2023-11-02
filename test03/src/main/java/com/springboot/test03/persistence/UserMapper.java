package com.springboot.test03.persistence;

import com.springboot.test03.domain.UserInfo;
import com.springboot.test03.util.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user order by regdate desc LIMIT #{postStart}, #{postCount}")
    public List<UserInfo> userInfoList(Page page);

    @Select("SELECT * FROM user WHERE #{searchType} LIKE concat('%', #{searchKeyword}, '%') order by regdate desc LIMIT #{postStart}, #{postCount}")
    public List<UserInfo> userInfoListSearch(Page page);

    @Select("SELECT * FROM user where id=#{id}")
    public UserInfo userInfoGet(String id);

    @Select("SELECT COUNT(*) FROM user")
    public int userCount();

    @Update("UPDATE user SET active = false WHERE id=#{id}")
    public void userResign(String id);

    @Update("UPDATE user SET pw=#{pw}, name=#{name}, email=#{email}, tel=#{tel} WHERE id=#{id}")
    public void userUpdate(UserInfo user);

    @Update("UPDATE user SET point = point+#{point} WHERE id=#{id}")
    public void userPointAdd(UserInfo user);

    @Update("UPDATE user SET type=#{type} WHERE id=#{id}")
    public void userTypeUpdate(UserInfo user);
}
