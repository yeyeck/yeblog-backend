package com.yeyeck.yeblog.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IEmailDaoTest {

    @Autowired
    IEmailDao emailDao;

    @Test
    void sendEmail() {
        emailDao.sendEmail("281782306@qq.com", "Hello", "test email");
    }

    @Test
    void sendHtmlEmail() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "xiaoming");
        emailDao.sendHtmlEmail("281782306@qq.com", "Hello", "test", map);
    }
}