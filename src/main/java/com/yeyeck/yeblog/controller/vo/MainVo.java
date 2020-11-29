package com.yeyeck.yeblog.controller.vo;

import com.yeyeck.yeblog.pojo.Article;
import com.yeyeck.yeblog.pojo.ArticleStatistics;
import com.yeyeck.yeblog.pojo.BlogSetting;
import com.yeyeck.yeblog.pojo.Link;
import lombok.Data;

import java.util.List;

@Data
public class MainVo {

    private BlogSetting blogSetting;

    private List<Link> navigations;

    private List<Link> footers;

    private List<Article> top10;

    private ArticleStatistics statistics;
}
