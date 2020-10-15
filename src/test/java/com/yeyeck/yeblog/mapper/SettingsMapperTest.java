package com.yeyeck.yeblog.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeyeck.yeblog.constants.YeConstants;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.EmailSettings;
import com.yeyeck.yeblog.pojo.MarkdownSetting;
import com.yeyeck.yeblog.pojo.Setting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SettingsMapperTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SettingsMapper settingsMapper;

    @Test
    void updateSetting() throws JsonProcessingException {
        MarkdownSetting markdownSetting = new MarkdownSetting();
        markdownSetting.setCodeStyle("default");
        Setting setting = new Setting();
        setting.setValue(objectMapper.writeValueAsString(markdownSetting));
        setting.setId(YeConstants.ID_SETTING_MARKDOWN);
        System.out.println(settingsMapper.updateSetting(setting));
    }

    @Test
    void getSetting() {
        BlogSetting emailSettings = settingsMapper.getBlogSetting(YeConstants.ID_SETTING_BASIC);
        System.out.println(emailSettings);
    }
}