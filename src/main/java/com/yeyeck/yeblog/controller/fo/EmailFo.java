package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmailFo {

    @NotEmpty
    @Email
    private String email;

    @NotNull
    private Integer type;

}
