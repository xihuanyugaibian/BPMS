package com.bpms.dao;

import com.bpms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User queryByName(String userName);

    List<User> queryByCondition(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("user") User user);
}