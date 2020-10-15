package com.yeyeck.yeblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class YeblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YeblogApplication.class, args);
    }

}
