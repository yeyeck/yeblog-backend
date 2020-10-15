package com.yeyeck.yeblog.controller.fo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class NicknameFo {

    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$")
    @Length(min = 2, max = 15)
    private String nickname;
}
