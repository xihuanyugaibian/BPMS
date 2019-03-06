package com.bpms.service.impl;

import com.bpms.dao.UserMapper;
import com.bpms.pojo.User;
import com.bpms.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User dologin(User user) {
        System.out.println(user.getUserName());
        User dbuser = userMapper.queryByName(user.getUserName());
        System.out.println(dbuser.getPassword());
        if (dbuser == null)
            return null;
        String loginPWD = new Md5Hash(user.getPassword(), dbuser.getSalt()).toString();
        System.out.println(loginPWD);
        if (dbuser.getPassword().equals(loginPWD))
            return dbuser;
        return null;
    }

    @Override
    public List<User> findUserByCondition(Integer pageNum, Integer pageSize, User user) {

        return userMapper.queryByCondition(pageNum, pageSize, user);
    }

    @Override
    public User findUserByName(String userName) {


        return userMapper.queryByName(userName);
    }
}
