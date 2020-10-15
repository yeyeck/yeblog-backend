package com.yeyeck.yeblog.constants;

import lombok.Getter;

@Getter
public enum LinkType {
    NAVIGATION(8), FOOTER(20), FRIEND(50);

    LinkType(int limit) {
        this.limit = limit;
    }
    int limit;
}
