package com.milkedu.milkedu.mapper;

import com.milkedu.milkedu.entity.UserAuthorities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAutoritiesMapper {
    @Select("SELECT * FROM authorities")
    public List<UserAuthorities> authList();

    @Select("SELECT * FROM authorities WHERE authorityNo=#{authorityNo}")
    public UserAuthorities authGet(int authorityNo);
}
