package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusFo {

    @NotNull
    private Integer status;
}
