package com.yeyeck.yeblog.pojo;

import lombok.Data;

@Data
public class Draft extends BasePojo{

    private Integer authorId;

    private String title;

    private String abstractText;

    private Integer status;

    private Integer views;

    private Integer countComments;

    private String contentMd;

    private String contentHtml;

}
