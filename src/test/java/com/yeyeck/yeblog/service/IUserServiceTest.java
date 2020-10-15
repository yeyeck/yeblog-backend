package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.UserFo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class IUserServiceTest {

    @Autowired
    IUserService userService;
}