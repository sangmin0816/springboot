package com.springboot.test03.service;

import com.springboot.test03.domain.UserInfo;
import com.springboot.test03.persistence.UserMapper;
import com.springboot.test03.util.Page;

import java.util.List;

public class UserServiceImpl implements UserService{
    UserMapper userMapper;

    @Override
    public List<UserInfo> userInfoList(Page page) {
        return userMapper.userInfoList(page);
    }
    @Override
    public List<UserInfo> userInfoListSearch(Page page) {
        return userMapper.userInfoListSearch(page);
    }

    @Override
    public UserInfo userInfoGet(String id) {
        return userMapper.userInfoGet(id);
    }

    @Override
    public int userCount() {
        return userMapper.userCount();
    }

    @Override
    public void userResign(String id) {
        userMapper.userResign(id);
    }

    @Override
    public void userUpdate(UserInfo user) {
        userMapper.userUpdate(user);
    }

    @Override
    public void userPointAdd(UserInfo user) {
        userMapper.userPointAdd(user);
    }

    @Override
    public void userTypeUpdate(UserInfo user) {
        userMapper.userTypeUpdate(user);
    }
}
