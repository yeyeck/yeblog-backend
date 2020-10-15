package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.pojo.User;
import com.yeyeck.yeblog.utils.ShiroUtils;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserFo {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;


    @Max(999999)
    @Min(100000)
    private Integer code;


    public User toUser() {
        User user = new User();
        user.setUsername(this.email);
        // 使用一个 uuid 生成默认昵称
        user.setNickname(ShiroUtils.generateSalt());
        // 对密码进行加密， 使用一个 uuid 作为加密盐
        String salt = ShiroUtils.generateSalt();
        String password = ShiroUtils.encrypt(salt, this.password);
        user.setSalt(salt);
        user.setPassword(password);
        user.setRole(YeConstants.USER_ROLE_MEMBER);
        return user;
    }

}
