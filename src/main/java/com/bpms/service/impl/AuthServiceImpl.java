package com.bpms.service.impl;

import com.bpms.dao.AuthMapper;
import com.bpms.pojo.Auth;
import com.bpms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional//在类上+注解，所有的方法都进行事务管理
public class AuthServiceImpl implements AuthService {

    @Autowired
    private  AuthMapper authMapper;
    @Override
    public List<Auth> findAuths(Integer userId, Integer parentId) {
        return authMapper.queryAuths(userId,parentId);
    }

    @Override
    public List<Auth> findAllAuths() {
        return authMapper.queryAll();
    }

    @Override
    public List<String> findPerms(Integer userId) {

        return authMapper.findPerms(userId);
    }

}
