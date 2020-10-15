package com.yeyeck.yeblog.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class User extends BasePojo implements Serializable {

    private String username;

    private String nickname;

    private String password;

    private String salt;

    private String role;
}
