package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void findByUsername() {
        User user = userMapper.findByUsername("Admin");
        log.info(user.toString());
    }

}