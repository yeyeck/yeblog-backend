package com.yeyeck.yeblog.pojo;

import lombok.Data;

@Data
public class Link extends BasePojo {

    private String label;

    private String type;

    private String link;

    private Boolean newBlank;

    private Integer orderNum;
}
