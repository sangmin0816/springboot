package com.chunjae.test01.persistence;

import com.chunjae.test01.domain.Test1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Test1Mapper {

    public List<Test1> test1List();

    @Select("SELECT * FROM test1")
    public List<Test1> test1List2();

    @Select("SELECT * FROM test1 WHERE no=#{no}")
    public Test1Mapper test1Get();
}
