package com.yeyeck.yeblog.dao.impl;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.dao.IEmailDao;
import com.yeyeck.yeblog.exception.EmailServiceNotSupportException;
import com.yeyeck.yeblog.pojo.EmailSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Component
@Slf4j
public class EmailDaoImpl implements IEmailDao {

    private JavaMailSender mailSender;

    private EmailSettings emailSettings;

    private TemplateEngine templateEngine;

    public EmailDaoImpl(JavaMailSender mailSender, EmailSettings emailSettings, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.emailSettings = emailSettings;
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean isEnabled() {
        return emailSettings.getOpened();
    }

    @Override
    public void checkStatus() {
        if (!isEnabled()) {
            throw new EmailServiceNotSupportException("本站点未开启邮箱服务，请联系管理员！");
        }
    }

    @Override
    @Async
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailSettings.getUsername());
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
    }

    @Override
    @Async
    public void sendHtmlEmail(String to, String subject, String templateName, Map<String, Object> variables) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, YeConstants.UTF_8);
            messageHelper.setFrom(emailSettings.getUsername());
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);

            Context context = new Context();
            context.setVariables(variables);
            String content = templateEngine.process(templateName, context);
            messageHelper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("系统出错啦");
        }
    }
}
