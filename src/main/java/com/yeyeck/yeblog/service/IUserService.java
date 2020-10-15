package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.AdminFo;
import com.yeyeck.yeblog.controller.fo.UserFo;
import com.yeyeck.yeblog.pojo.User;

public interface IUserService {

    boolean updateNickname(String nickname);

    boolean updateAdmin(AdminFo adminVo);
}
