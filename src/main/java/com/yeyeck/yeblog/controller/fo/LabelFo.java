package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LabelFo {
    @NotEmpty
    private String name;
}
