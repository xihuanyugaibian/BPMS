package com.bpms.service.impl;

import com.bpms.dao.RoleMapper;
import com.bpms.pojo.Role;
import com.bpms.service.RoleServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServcieImpl implements RoleServcie {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoles() {
        return roleMapper.queryRoles();
    }

    @Override
    public List<Role> findRolesByCondition(Integer pageNum, Integer pageSize, Role role) {
        return roleMapper.queryRolesByCondition(pageNum, pageSize, role);
    }

    @Override
    public String getAuthIds(Integer roleId) {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (role==null)
            return null;
        return role.getAuthIds();
    }
}
