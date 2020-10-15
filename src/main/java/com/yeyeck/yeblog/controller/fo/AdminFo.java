package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.User;
import com.yeyeck.yeblog.utils.ShiroUtils;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminFo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    public User toUser() {
        User user = new User();
        user.setUsername(this.username);
        // 对密码进行加密， 使用一个 uuid 作为加密盐
        String salt = ShiroUtils.generateSalt();
        String password = ShiroUtils.encrypt(salt, this.password);
        user.setSalt(salt);
        user.setPassword(password);
        return user;
    }

}
