package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.AdminFo;
import com.yeyeck.yeblog.exception.ParamException;
import com.yeyeck.yeblog.mapper.UserMapper;
import com.yeyeck.yeblog.pojo.User;
import com.yeyeck.yeblog.service.IUserService;
import com.yeyeck.yeblog.utils.ShiroUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean updateNickname(String nickname) {
        User currentUser  = ShiroUtils.getCurrentUser();
        // 只对当前登陆用户进行修改
        if (userMapper.updateNickname(currentUser.getId(), nickname) < 1) {
            throw new RuntimeException("系统错误!");
        }
        // 更新当前内存用户的昵称
        currentUser.setNickname(nickname);
        return true;
    }

    @Override
    public boolean updateAdmin(AdminFo adminFo) {
        if (!adminFo.getPassword().equals(adminFo.getConfirmPassword())) {
            throw new ParamException("两次输入密码不一致");
        }
        User currentUser = ShiroUtils.getCurrentUser();
        if (currentUser.getId() != YeConstants.ID_SUPER_ADMIN) {
            throw new UnauthorizedException();
        }
        User user = adminFo.toUser();
        user.setId(currentUser.getId());
        if (userMapper.updateAdmin(user) < 1) {
            throw new RuntimeException("数据库更新错误！");
        }
        currentUser.setUsername(user.getUsername());
        return true;
    }
}
