package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class LoginFo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


    private boolean rememberMe = false;

}
