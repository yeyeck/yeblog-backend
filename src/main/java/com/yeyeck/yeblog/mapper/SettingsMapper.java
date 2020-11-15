package com.yeyeck.yeblog.mapper;

import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.EmailSettings;
import com.yeyeck.yeblog.pojo.MarkdownSetting;
import com.yeyeck.yeblog.pojo.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SettingsMapper {

    @Update("update t_settings set json_value = #{value} where id = #{id}")
    int updateSetting(Setting setting);

    @Select("select json_value->>'$.codeStyle' as code_style from t_settings where id = #{id}")
    MarkdownSetting getMarkdownSetting(int id);

    @Select("select json_value->> '$.username' as username, " +
            "json_value->> '$.password' as password, " +
            "json_value->> '$.opened' as opened, " +
            "json_value->> '$.host' as host " +
            "from t_settings where id = #{id}")
    EmailSettings getEmailSettings(int id);


    @Select("select json_value->> '$.siteName' as site_name, " +
            "json_value->> '$.domain' as domain, " +
            "json_value->> '$.subTitle' as sub_title, " +
            "json_value->> '$.icpRecord' as icp_record, " +
            "json_value->> '$.icpRecordUrl' as icp_record_url, " +
            "json_value->> '$.psRecord' as ps_record, " +
            "json_value->> '$.psRecordUrl' as ps_record_url, " +
            "json_value->> '$.description' as description, " +
            "json_value->> '$.keywords' as keywords " +
            "from t_settings where id = #{id}")
    BlogSetting getBlogSetting(int id);
}
