package com.tmall.project5.service;

import com.tmall.project5.entity.User;
import com.tmall.project5.util.Page;

import java.util.List;

public interface UserService {
    public List<User> userList(Page page);

    public int userCount(Page page);

    public User userGet(String id);
    public User userFindByTel(String name, String tel);
    public User userFindByEmail(String name, String email);

    public int userIdCheck(String id);

    public int userInsert(User user);

    public int userUpdate(User user);
    public int userPointUpdate(String id, int point);
    public int userVerifyUpdate(String id, boolean verify);
    public int userActiveUpdate(String id, boolean active);

    public int userDelete(String id);

    public boolean userLogin(String id, String pw);
}
