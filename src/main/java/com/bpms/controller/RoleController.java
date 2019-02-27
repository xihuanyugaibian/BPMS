package com.bpms.controller;

import com.bpms.pojo.Role;
import com.bpms.service.RoleServcie;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServcie roleServcie;

    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList() {
        return roleServcie.findRoles();
    }

    @RequestMapping("/dataList")
    @ResponseBody
    public Map<String, Object> dataList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum, @RequestParam(value = "rows", required = false, defaultValue = "5") Integer pageSize, Role role) {
        List<Role> roles = roleServcie.findRolesByCondition(pageNum, pageSize, role);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", roles);
        map.put("total", new PageInfo<>(roles).getTotal());
        return map;
    }

}
