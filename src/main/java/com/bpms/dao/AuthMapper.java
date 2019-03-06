package com.bpms.dao;

import com.bpms.pojo.Auth;

import java.util.List;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    int insertSelective(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Auth> queryAuths(Integer userId, Integer parentId);

    List<Auth> queryAll();

    List<String> findPerms(Integer userId);
}