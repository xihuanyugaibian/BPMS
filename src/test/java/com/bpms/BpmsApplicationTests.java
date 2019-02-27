package com.bpms;

import com.bpms.pojo.Auth;
import com.bpms.pojo.Role;
import com.bpms.service.AuthService;
import com.bpms.service.RoleServcie;
import com.bpms.service.UserService;
import com.bpms.util.TreeNode;
import com.bpms.util.TreeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//测试类不需要加特备多额代码
@RunWith(SpringRunner.class)
@SpringBootTest
public class BpmsApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private RoleServcie roleServcie;

    @Test
    public void contextLoads() {
        //权限递归测试
        String authIds = roleServcie.getAuthIds(1);
        List<Auth> auths = authService.findAllAuths();
        List<TreeNode> treeNodes = TreeUtil.authsToNode(auths, authIds, -1);
        for (TreeNode node : treeNodes) {
            System.out.println(node);
        }


        //角色分页查询测试
        //Role role = new Role();
        ////role.setRoleName("理");
        //List<Role> roles = roleServcie.findRolesByCondition(1, 5, role);
        //for (Role role1 : roles) {
        //    System.out.println(role1.getRoleName());
        //
        //}


        //角色下拉框 测试
        //List<Role> roles = roleServcie.findRoles();
        //for (Role role : roles) {
        //    System.out.println(role.getRoleName());
        //
        //
        //}


        ////分页查询,动态查询
        //User user = new User();
        ////user.setUserName("a");
        //List<User> users = userService.findUserByCondition(1, 5, null);
        //for (User user1 : users) {
        //    System.out.println(user1.getUserName());
        //
        //}


        //当前用户权限查询
        //List<Auth> auths = authService.findAuths(1, 2);
        //for (Auth auth : auths) {
        //    System.out.println("authName"+auth.getAuthName());
        //    System.out.println("authPath"+auth.getAuthPath());
        //
        //}
        //List<TreeNode> nodes = TreeUtil.authsToNode(auths);
        //for (TreeNode node : nodes) {
        //    System.out.println(node.getAttributes().get("authPath"));
        //}


        //登录测试
        //User user = new User();
        //user.setUserName("admin");
        //user.setPassword("123");
        //System.out.println("结果" + userService.dologin(user));


    }

}
