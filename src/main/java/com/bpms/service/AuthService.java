package com.bpms.service;

import com.bpms.pojo.Auth;

import java.util.List;

public interface AuthService {
    List<Auth> findAuths(Integer userId, Integer parentId);

    List<Auth> findAllAuths();

    List<String> findPerms(Integer userId);
}
