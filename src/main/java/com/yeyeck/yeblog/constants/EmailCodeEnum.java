package com.yeyeck.yeblog.constants;

public enum EmailCodeEnum {

    REGISTER(1, "REGISTER-CODE-%s", "用户注册"),
    CHANGE_PASSWORD(2, "PASSWORD-CODE-%S", "密码修改");

    private int id;
    private String keyFormat;
    private String action;

    EmailCodeEnum(int id, String keyFormat, String action) {
        this.id = id;
        this.keyFormat = keyFormat;
        this.action = action;
    }

    public static EmailCodeEnum get(int id) {
        EmailCodeEnum[] values = EmailCodeEnum.values();
        for (EmailCodeEnum value : values) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getKeyFormat() {
        return keyFormat;
    }

    public String getAction() {
        return action;
    }
}
