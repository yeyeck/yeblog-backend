package com.yeyeck.yeblog.dao;

import com.yeyeck.yeblog.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class IRedisDaoTest {

    @Autowired
    IRedisDao redisDao;


    @Test
    void get() {
        User user = (User)redisDao.get("user");
        log.info(user.toString());
    }

    @Test
    void add() {
        User user = new User();
        user.setNickname("admin");
        user.setPassword("hello");
        user.setRole("admin");
        user.setSalt("wdwdw");
        user.setUsername("admin");
        redisDao.add("user", user);
    }

    @Test
    void testAdd() {
        long res = redisDao.hyperLogLogSize("/article/19");
        log.info("浏览量: {}", res);
    }

    @Test
    void getList() {
        List<User> users = redisDao.getList("user-list");
        log.info(users.toString());
    }

    @Test
    void addToList() {
        User user1 = new User();
        user1.setNickname("admin");
        user1.setPassword("hello");
        user1.setRole("admin");
        user1.setSalt("wdwdw");
        user1.setUsername("admin");

        User user2 = new User();
        user2.setNickname("admin2");
        user2.setPassword("hello");
        user2.setRole("admin2");
        user2.setSalt("wdwdw");
        user2.setUsername("admin");

        redisDao.addToList("user-list", user1, user2);

    }

    @Test
    void testGet() {
    }

    @Test
    void testAdd1() {
    }

    @Test
    void testAdd2() {
    }

    @Test
    void testAddToList() {
    }

    @Test
    void testGetList() {
    }

    @Test
    void addString() {
    }

    @Test
    void testAddString() {
    }

    @Test
    void getString() {
    }

    @Test
    void hyperLogLogSize() {
    }

    @Test
    void hyperLogLogAdd() {
    }

    @Test
    void delete() {
    }

    @Test
    void keys() {
        Set<String> keys = redisDao.keys("*");
        log.info(keys.toString());
    }

    @Test
    void hasKey() {
    }
}