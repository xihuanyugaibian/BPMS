package com.bpms.service;

import com.bpms.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * @param user
     * @return 1登录成功  null账号不存在  0密码不正确
     */
    User dologin(User user);

    List<User> findUserByCondition(Integer pageNum, Integer pageSize, User user);

    User findUserByName(String userName);
}
