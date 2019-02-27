package com.bpms.dao;

import com.bpms.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryRoles();

    List<Role> queryRolesByCondition(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("role") Role role);
}