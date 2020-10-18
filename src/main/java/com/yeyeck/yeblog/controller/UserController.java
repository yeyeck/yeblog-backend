package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.*;
import com.yeyeck.yeblog.pojo.User;
import com.yeyeck.yeblog.service.IUserService;
import com.yeyeck.yeblog.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User login(@Validated @RequestBody LoginFo loginFo) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginFo.getUsername(), loginFo.getPassword());
        token.setRememberMe(loginFo.isRememberMe());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = ShiroUtils.getCurrentUser();
        User curUser = new User();
        curUser.setNickname(user.getNickname());
        curUser.setRole(user.getRole());
        return curUser;
    }

    @GetMapping("/me")
    @RequiresAuthentication
    public User currentUser() {
        User currentUser = ShiroUtils.getCurrentUser();
        User user = new User();
        user.setUsername(currentUser.getUsername());
        user.setNickname(currentUser.getNickname());
        user.setCreateTime(currentUser.getCreateTime());
        return user;
    }

    @RequiresAuthentication
    @PutMapping("/nickname")
    public boolean updateNickname (@Validated @RequestBody NicknameFo nicknameVo) {
        return userService.updateNickname(nicknameVo.getNickname());
    }

    @PutMapping("/logout")
    public boolean logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return true;
    }

    @PutMapping("/admin")
    @RequiresAuthentication
    public boolean updateAdmin(@Validated @RequestBody AdminFo adminVo) {
        return userService.updateAdmin(adminVo);
    }
}
