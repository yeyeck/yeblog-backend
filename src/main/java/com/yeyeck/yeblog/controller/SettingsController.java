package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.constants.LinkType;
import com.yeyeck.yeblog.controller.fo.Batch;
import com.yeyeck.yeblog.controller.fo.BlogSettingFo;
import com.yeyeck.yeblog.controller.fo.LinkFo;
import com.yeyeck.yeblog.controller.fo.LinkOrderNumFo;
import com.yeyeck.yeblog.controller.vo.MainVo;
import com.yeyeck.yeblog.pojo.*;
import com.yeyeck.yeblog.service.IArticleService;
import com.yeyeck.yeblog.service.ILinkService;
import com.yeyeck.yeblog.service.ISettingsService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/settings")
@RestController
public class SettingsController {

    private ISettingsService settingsService;

    private ILinkService linkService;

    private IArticleService articleService;

    public SettingsController(ISettingsService settingsService, ILinkService linkService, IArticleService articleService) {
        this.settingsService = settingsService;
        this.linkService = linkService;
        this.articleService = articleService;
    }

    @GetMapping("/blog")
    public BlogSetting getBlogSettings() {
        return settingsService.getBlogSetting();
    }

    @GetMapping("/main")
    public MainVo getMainSettings() {
        BlogSetting blogSetting = settingsService.getBlogSetting();
        List<Link> navigations = linkService.getLinkByType(LinkType.NAVIGATION.name().toLowerCase());
        List<Link> footers = linkService.getLinkByType(LinkType.FOOTER.name().toLowerCase());
        List<Link> friends = linkService.getLinkByType(LinkType.FRIEND.name().toLowerCase());
        List<Article> top10 = articleService.getTop10();
        ArticleStatistics statistics = articleService.getStatistics();
        MainVo mainVo = new MainVo();
        mainVo.setBlogSetting(blogSetting);
        mainVo.setFooters(footers);
        mainVo.setNavigations(navigations);
        mainVo.setFriends(friends);
        mainVo.setTop10(top10);
        mainVo.setStatistics(statistics);
        return mainVo;
    }

    @GetMapping("/email")
    @RequiresAuthentication
    public EmailSettings getEmailSettings() {
        return settingsService.getEmailSetting();
    }


    @PutMapping("/email")
    @RequiresAuthentication
    public boolean updateEmailSetting (@Validated @RequestBody EmailSettings emailSettings) {
        return settingsService.setEmailSetting(emailSettings);
    }

    @PutMapping("/blog")
    @RequiresAuthentication
    public boolean updateBlogSetting (@Validated @RequestBody BlogSettingFo blogSettingVo) {
        return settingsService.setBlogSetting(blogSettingVo);
    }

    @GetMapping("/links")
    @RequiresAuthentication
    public List<Link> getLinks() {
        return linkService.getAll();
    }

    @GetMapping("/links/{type}")
    @RequiresAuthentication
    public List<Link> getLinksByType(@PathVariable("type") String type) {
        return linkService.getLinkByType(type);
    }

    @PostMapping("/link")
    @RequiresAuthentication
    public Link addLink(@Validated @RequestBody LinkFo linkFo) {
        return linkService.addLink(linkFo);
    }

    @PutMapping("/link/{id}")
    @RequiresAuthentication
    public boolean updateLink(@PathVariable("id") Integer id, @Validated @RequestBody LinkFo linkFo) {
        return linkService.updateLink(id, linkFo);
    }

    @DeleteMapping("/link/{id}")
    @RequiresAuthentication
    public boolean deleteLink(@PathVariable("id") Integer id) {
        return linkService.deleteLinkById(id);
    }

    @PostMapping("/delete/links")
    @RequiresAuthentication
    public boolean deleteByIds (@RequestBody Batch<Integer> batch) {
        return linkService.deleteLinkByIds(batch.getObjects());
    }

    @PatchMapping("/link/orderNum/{id}")
    @RequiresAuthentication
    public boolean setOrderNum(@PathVariable("id") Integer id, @Validated @RequestBody LinkOrderNumFo linkOrderNumFo) {
        return linkService.updateOrderNum(id, linkOrderNumFo.getOrderNum(), linkOrderNumFo.getType());
    }

}
