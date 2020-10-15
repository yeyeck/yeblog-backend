package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.controller.fo.BlogSettingFo;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.EmailSettings;

public interface ISettingsService {

    BlogSetting getBlogSetting();

    EmailSettings getEmailSetting();

    boolean setBlogSetting(BlogSettingFo blogSettingFo);

    boolean setEmailSetting(EmailSettings emailSettings);
}
