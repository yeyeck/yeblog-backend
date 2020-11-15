package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.BlogSetting;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BlogSettingFo {

    @NotEmpty
    private String siteName;

    private String subTitle = "";

    private String domain = "";

    private String icpRecord = "";

    private String icpRecordUrl = "";

    private String psRecord = "";

    private String psRecordUrl = "";

    private String description = "";

    private String keywords = "";

    public BlogSetting toBlogSetting () {
        BlogSetting blogSetting = new BlogSetting();
        blogSetting.setSiteName(this.siteName);
        blogSetting.setSubTitle(this.subTitle);
        blogSetting.setDescription(this.description);
        blogSetting.setDomain(this.domain);
        blogSetting.setIcpRecord(this.icpRecord);
        blogSetting.setIcpRecordUrl(this.icpRecordUrl);
        blogSetting.setPsRecord(this.psRecord);
        blogSetting.setPsRecordUrl(this.psRecordUrl);
        blogSetting.setKeywords(this.keywords);
        return blogSetting;
    }
}
