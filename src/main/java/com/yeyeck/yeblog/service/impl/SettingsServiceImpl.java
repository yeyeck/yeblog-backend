package com.yeyeck.yeblog.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.controller.fo.BlogSettingFo;
import com.yeyeck.yeblog.exception.EmailAuthenticationInvalidException;
import com.yeyeck.yeblog.exception.ParamException;
import com.yeyeck.yeblog.mapper.SettingsMapper;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.EmailSettings;
import com.yeyeck.yeblog.pojo.Setting;
import com.yeyeck.yeblog.service.ISettingsService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Properties;

@Service
public class SettingsServiceImpl implements ISettingsService {

    private ObjectMapper objectMapper;

    private SettingsMapper settingsMapper;

    private BlogSetting blogSetting;

    private EmailSettings emailSettings;

    private JavaMailSender javaMailSender;

    public SettingsServiceImpl(SettingsMapper settingsMapper, BlogSetting blogSetting, EmailSettings emailSettings,
                               ObjectMapper objectMapper, JavaMailSender javaMailSender) {
        this.settingsMapper = settingsMapper;
        this.blogSetting = blogSetting;
        this.emailSettings = emailSettings;
        this.objectMapper = objectMapper;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public BlogSetting getBlogSetting() {
        return this.blogSetting;
    }

    @Override
    public EmailSettings getEmailSetting() {
        return this.emailSettings;
    }

    @Override
    public boolean setBlogSetting(BlogSettingFo blogSettingFo) {
        BlogSetting blogSetting = blogSettingFo.toBlogSetting();
        Setting setting = new Setting();
        try {
            String value = objectMapper.writeValueAsString(blogSetting);
            setting.setValue(value);
            setting.setId(YeConstants.ID_SETTING_BASIC);
        } catch (JsonProcessingException e) {
            throw new ParamException("BlogSettingFo 转 JSON 失败：  " + blogSetting.toString());
        }
        if (settingsMapper.updateSetting(setting) < 1) {
            throw new RuntimeException("更新数据库失败");
        }
        this.blogSetting.setSiteName(blogSetting.getSiteName());
        this.blogSetting.setDescription(blogSetting.getDescription());
        this.blogSetting.setKeywords(blogSetting.getKeywords());
        this.blogSetting.setDomain(blogSetting.getDomain());
        this.blogSetting.setIcpRecord(blogSetting.getIcpRecord());
        this.blogSetting.setIcpRecordUrl(blogSetting.getIcpRecordUrl());
        this.blogSetting.setPsRecord(blogSetting.getPsRecord());
        this.blogSetting.setPsRecordUrl(blogSetting.getPsRecordUrl());
        return false;
    }

    @Override
    public boolean setEmailSetting(EmailSettings emailSettings) {
        // 1. 若开启邮箱服务，测试邮箱信息是否有效
        if (emailSettings.getOpened()) {
            testConnection(emailSettings);
        }
        // 2. 更新数据库
        Setting setting = new Setting();
        try {
            String value = objectMapper.writeValueAsString(emailSettings);
            setting.setValue(value);
            setting.setId(YeConstants.ID_SETTING_EMAIL);
        } catch (JsonProcessingException e) {
            throw new ParamException("EmailSetting 转 JSON 失败：  " + emailSettings.toString());
        }
        if (settingsMapper.updateSetting(setting) < 1) {
            throw new RuntimeException("更新数据库失败");
        }
        // 3. 更新当前邮箱设置
        this.emailSettings.setOpened(emailSettings.getOpened());
        this.emailSettings.setHost(emailSettings.getHost());
        this.emailSettings.setUsername(emailSettings.getUsername());
        this.emailSettings.setPassword(emailSettings.getPassword());

        // 4. 更新 JavaMailSender
        if (emailSettings.getOpened()) {
            JavaMailSenderImpl sender = (JavaMailSenderImpl)javaMailSender;
            setMailSenderProperties(sender, emailSettings);
        }
        return true;
    }


    private void testConnection(EmailSettings emailSettings) {
        if (emailSettings.getOpened()) {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            setMailSenderProperties(sender, emailSettings);
            try {
                sender.testConnection();
            } catch (MessagingException e) {
                throw new EmailAuthenticationInvalidException("邮箱连接测试失败，请检查邮箱信息或稍后再尝试");
            }
        }
    }

    private void setMailSenderProperties(JavaMailSenderImpl sender, EmailSettings emailSettings) {
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
}
