package com.bpms.controller;

import com.bpms.pojo.Auth;
import com.bpms.pojo.User;
import com.bpms.service.AuthService;
import com.bpms.service.RoleServcie;
import com.bpms.util.StringBean;
import com.bpms.util.TreeNode;
import com.bpms.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RoleServcie roleServcie;

    @Autowired
    private AuthService authService;

    @RequestMapping("/loadTreeNode")
    @ResponseBody
    public List<TreeNode> loadTreeNode(@RequestParam(value = "id", required = false, defaultValue = "-1") Integer parentId, HttpSession session) {
        User user = (User) session.getAttribute(StringBean.CURRENT_USER);
        List<Auth> auths = authService.findAuths(user.getUserId(), parentId);
        return TreeUtil.authsToNode(auths);
    }

    @RequestMapping("/loadAuthsTree/{roleId}")
    @ResponseBody
    public List<TreeNode> loadAuthsTree(@PathVariable("roleId") Integer roleId) {
        List<Auth> auths = authService.findAllAuths();
        String authIds = roleServcie.getAuthIds(roleId);
        return TreeUtil.authsToNode(auths, authIds, -1);
    }


    @RequestMapping("/dataList")
    @ResponseBody
    public List<Auth> dataList() {
        List<Auth> auths = authService.findAllAuths();
        return TreeUtil.authsToNode_treegrid(auths, -1);
    }
}
