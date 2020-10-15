package com.yeyeck.yeblog.dao;

import java.util.Map;

public interface IEmailDao {

    boolean isEnabled();

    void checkStatus();

    void sendEmail(String to, String subject, String content);

    void sendHtmlEmail(String to, String subject, String templateName, Map<String, Object> variables);
}
