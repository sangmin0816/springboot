package com.springboot.test03.service;

import com.springboot.test03.domain.UserInfo;
import com.springboot.test03.util.Page;

import java.util.List;

public interface UserService {
    public List<UserInfo> userInfoList(Page page);
    public List<UserInfo> userInfoListSearch(Page page);
    public UserInfo userInfoGet(String id);
    public int userCount();
    public void userResign(String id);
    public void userUpdate(UserInfo user);
    public void userPointAdd(UserInfo user);
    public void userTypeUpdate(UserInfo user);

}
