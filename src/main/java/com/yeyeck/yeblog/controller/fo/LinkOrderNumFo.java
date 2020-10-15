package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class LinkOrderNumFo {

    @NotBlank
    private String type;

    @Min(0)
    @Max(20)
    private Integer orderNum;

}
