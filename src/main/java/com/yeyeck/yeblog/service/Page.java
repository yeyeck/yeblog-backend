package com.yeyeck.yeblog.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class Page<T> {

    @Setter
    @Getter
    private int current = 1;

    @Setter
    @Getter
    private int countPerPage = 10;

    @Setter
    @Getter
    private int start = 0;

    @Setter
    @Getter
    private String orderParam = "create_time";

    @Setter
    @Getter
    private String orderType = "DESC";

    @Getter
    @Setter
    private String filterName = "1";    // 精确筛选

    @Getter
    @Setter
    private Object filterValue = "1";

    @Getter
    private int totalCount;

    @Getter
    private int totalPage;

    @Getter
    @Setter
    private List<T> data;

    public Page(int pageNum) {

        this.current = pageNum;
        this.start = (current - 1) * countPerPage;

    }

    public Page(int totalCount, int pageNum) {
        this(pageNum);
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount % this.countPerPage == 0) ? (this.totalCount / this.countPerPage) : (this.totalCount / this.countPerPage + 1);
    }

    public Page(int current, int totalCount, String orderParam, String orderType) {
       this(current, 10, totalCount, orderParam, orderType);
    }

    public Page(int current, int countPerPage, int totalCount, String orderParam, String orderType) {
        this.current = current;
        this.countPerPage = countPerPage;
        this.start = (current - 1) * countPerPage;
        this.orderParam = orderParam;
        this.orderType = orderType;
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount % this.countPerPage == 0) ? (this.totalCount / this.countPerPage) : (this.totalCount / this.countPerPage + 1);
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount % this.countPerPage == 0) ? (this.totalCount / this.countPerPage) : (this.totalCount / this.countPerPage + 1);
    }
}
