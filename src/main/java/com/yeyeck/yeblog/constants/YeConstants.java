package com.yeyeck.yeblog.constants;


public class YeConstants {
    // 字符编码 utf-8
    public static final String UTF_8 = "UTF-8";

    public static final int ID_SUPER_ADMIN = 1;

    // 文章状态
    public static final int ARTICLE_STATUS_PUBLISHED = 1;
    public static final int ARTICLE_STATUS_DRAFT = 0;

    // 评论状态
    public static final int COMMENT_STATUS_PASSED = 1;
    public static final int COMMENT_STATUS_NOT_PASSED = 0;

    // 配置id
    public static final int ID_SETTING_BASIC = 1;
    public static final int ID_SETTING_MARKDOWN = 2;
    public static final int ID_SETTING_EMAIL = 3;

    // email subject
    public static final String EMAIL_SUBJECT_CODE = "【%s】用户验证码服务";


    // 邮件模板
    public static final String TEMPLATE_REGISTER_CODE = "register";

    // 用户密码加密用
    public static final String HASH_TYPE = "MD5";
    public static final int HASH_TIMES = 5;

    //角色
    public static final String USER_ROLE_ADMIN = "admin";
    public static final String USER_ROLE_GUEST = "guest";
    public static final String USER_ROLE_MEMBER = "member";
    public static final String USER_ROLE_AUTHOR = "author";

    // redis
    public static final int REGISTER_CODE_MINUTES = 15;

    // 系统图片
    public static final String IMAGE_LOGO = "logo";
    public static final String IMAGE_HEADER = "header";

    private YeConstants(){}
}
