package com.yeyeck.yeblog.configuration;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.mapper.SettingsMapper;
import com.yeyeck.yeblog.pojo.EmailSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfiguration {

    private SettingsMapper settingsMapper;

    public EmailConfiguration(SettingsMapper settingsMapper) {
        this.settingsMapper = settingsMapper;
    }

    @Bean
    public JavaMailSender javaMailSender(EmailSettings emailSettings) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (emailSettings.getOpened()) {
            sender.setUsername(emailSettings.getUsername());
            sender.setPassword(emailSettings.getPassword());
            sender.setHost(emailSettings.getHost());
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");//开启认证
            properties.setProperty("mail.smtp.timeout", "200000");//设置链接超时
            properties.setProperty("mail.smtp.port", Integer.toString(25));//设置端口
            properties.setProperty("mail.smtp.socketFactory.port", Integer.toString(465));//设置ssl端口
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            sender.setJavaMailProperties(properties);
        }
        return sender;
    }

}
