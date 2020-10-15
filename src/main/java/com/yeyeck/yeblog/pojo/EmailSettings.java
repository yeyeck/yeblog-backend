package com.yeyeck.yeblog.pojo;

import lombok.Data;

@Data
public class EmailSettings {

    private String username = "";

    private String password = "";

    private Boolean opened = false;

    private String host = "";

}
