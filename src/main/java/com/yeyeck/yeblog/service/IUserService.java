package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.AdminFo;

public interface IUserService {

    boolean updateNickname(String nickname);

    boolean updateAdmin(AdminFo adminVo);
}
