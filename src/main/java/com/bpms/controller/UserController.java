package com.bpms.controller;

import com.bpms.pojo.User;
import com.bpms.service.UserService;
import com.bpms.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/dologin")
    @ResponseBody
    public ResultUtil dologin(User user, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            subject.login(token);
            System.out.println("认证通过");
            return ResultUtil.ok();
        } catch (UnknownAccountException e) {
            System.out.println("账户不对");
            ResultUtil.error("账号或者密码错误");
        } catch (AuthenticationException e) {
            System.out.println("认证失败");
            return ResultUtil.error("账号或者密码错误");
        }
        return null;
        //User dbuser = userService.dologin(user);
        //if (dbuser != null) {
        //    session.setAttribute(StringBean.CURRENT_USER, dbuser);
        //    return ResultUtil.ok();
        //}
        //return ResultUtil.error("账号或者密码错误");
    }

    @RequestMapping("/dataList")
    @ResponseBody
    public Map<String, Object> dataList(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "rows", required = false, defaultValue = "5") Integer pageSize, User user) {
        List<User> users = userService.findUserByCondition(pageNum, pageSize, user);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", users);
        map.put("total", new PageInfo<>(users).getTotal());
        return map;
    }


}
