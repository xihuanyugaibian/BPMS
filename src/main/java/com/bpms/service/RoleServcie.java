package com.bpms.service;

import com.bpms.pojo.Role;

import java.util.List;

public interface RoleServcie {
    List<Role> findRoles();

    List<Role> findRolesByCondition(Integer pageNum, Integer pageSize, Role role);

    String getAuthIds(Integer roleId);
}
