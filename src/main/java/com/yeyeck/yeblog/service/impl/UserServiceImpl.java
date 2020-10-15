package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.constants.EmailCodeEnum;
import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.AdminFo;
import com.yeyeck.yeblog.controller.fo.UserFo;
import com.yeyeck.yeblog.dao.IEmailDao;
import com.yeyeck.yeblog.dao.IRedisDao;
import com.yeyeck.yeblog.exception.DataConflictException;
import com.yeyeck.yeblog.exception.NoSuchDataException;
import com.yeyeck.yeblog.exception.ParamException;
import com.yeyeck.yeblog.mapper.UserMapper;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.User;
import com.yeyeck.yeblog.service.IUserService;
import com.yeyeck.yeblog.utils.KeyUtils;
import com.yeyeck.yeblog.utils.ShiroUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class UserServiceImpl implements IUserService {

    private IEmailDao emailDao;

    private IRedisDao redisDao;

    private BlogSetting blogSetting;

    private UserMapper userMapper;

    public UserServiceImpl(IEmailDao emailDao, IRedisDao redisDao,
                           BlogSetting blogSetting, UserMapper userMapper) {
        this.emailDao = emailDao;
        this.redisDao = redisDao;
        this.blogSetting = blogSetting;
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

    private void checkCode(String codeKey, Integer code) {
        String value = redisDao.getString(codeKey);
        if (value == null) {
            throw  new ParamException("验证码不存在或已过期，请重新申请验证码");
        }
        if (!value.equals(String.valueOf(code))) {
            throw new ParamException("验证码错误，请前往邮箱查看验证码");
        }
    }

    private int generateCode() {
        Random random = new Random();
        return random.nextInt(899999) + 100000;
    }
}
