package com.yeyeck.yeblog.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class BasePojo {

    private int id;    // 表主键

    private Date createTime;    // 记录插入时间

    private Date updateTime;    // 最后更新时间

}
