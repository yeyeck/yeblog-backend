package com.yeyeck.yeblog.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Common {
    @Test
    void stringMatches() {
        String str = "/article/14";
        log.info("res: {}", str.matches("^/article/[1-9][0-9]*$"));
    }
}
