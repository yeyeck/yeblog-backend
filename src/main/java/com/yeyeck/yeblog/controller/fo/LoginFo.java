package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class LoginFo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


    private boolean rememberMe = false;

}
