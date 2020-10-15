package com.yeyeck.yeblog.configuration;

import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.mapper.SettingsMapper;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.EmailSettings;
import com.yeyeck.yeblog.pojo.MarkdownSetting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YeblogConfiguration {

    private SettingsMapper settingsMapper;

    public YeblogConfiguration(SettingsMapper settingsMapper) {
        this.settingsMapper = settingsMapper;
    }

//    @Bean
//    public MarkdownSetting markdownSetting() {
//        return settingsMapper.getMarkdownSetting(YeConstants.ID_SETTING_MARKDOWN);
//    }

    @Bean
    public EmailSettings emailSettings() {
        return settingsMapper.getEmailSettings(YeConstants.ID_SETTING_EMAIL);
    }

    @Bean
    public BlogSetting blogSetting() { return settingsMapper.getBlogSetting(YeConstants.ID_SETTING_BASIC); }
}
