package com.tmall.project5.service;

import com.tmall.project5.entity.User;
import com.tmall.project5.mapper.UserMapper;
import com.tmall.project5.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> userList(Page page) {
        String searchType = page.getSearchType();
        if(searchType==null || searchType.equals("")){
            return userMapper.userList(page);
        }
        return userMapper.userSearchList(page);
    }

    @Override
    public int userCount(Page page) {
        String searchType = page.getSearchType();
        if(searchType==null || searchType.equals("")){
            return userMapper.userCount(page);
        }
        return userMapper.userSearchCount(page);
    }

    @Override
    public User userGet(String id) {
        return userMapper.userGet(id);
    }

    @Override
    public User userFindByTel(String name, String tel) {
        return userMapper.userFindByEmail(name, tel);
    }

    @Override
    public User userFindByEmail(String name, String email) {
        return userMapper.userFindByTel(name, email);
    }

    @Override
    public int userIdCheck(String id) {
        return userMapper.userIdCheck(id);
    }

    @Override
    public int userInsert(User user) {
        return userMapper.userInsert(user);
    }

    @Override
    public int userUpdate(User user) {
        return userMapper.userUpdate(user);
    }

    @Override
    public int userPointUpdate(String id, int point) {
        return userMapper.userPointUpdate(id, point);
    }

    @Override
    public int userVerifyUpdate(String id, boolean verify) {
        return userMapper.userVerifyUpdate(id, verify);
    }

    @Override
    public int userActiveUpdate(String id, boolean active) {
        return userMapper.userActiveUpdate(id, active);
    }

    @Override
    public int userDelete(String id) {
        return userMapper.userDelete(id);
    }

    @Override
    public boolean userLogin(String id, String pw) {
        boolean loginsuccess = false;
        User user = userMapper.userGet(id);
        if(passwordEncoder.matches(pw, user.getPw())){
            loginsuccess = true;
        }

        return loginsuccess;
    }
}
