package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.controller.fo.LinkFo;
import com.yeyeck.yeblog.service.ILinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LinkServiceImplTest {

    @Autowired
    ILinkService linkService;

    @Test
    void addLink() {
        LinkFo linkFo = new LinkFo();
        linkFo.setLabel("MAD");
        linkFo.setType("friend");
        linkFo.setLink("http://blog.com");
        linkFo.setNewBlank(true);
        linkService.addLink(linkFo);
    }

    @Test
    void deleteLinkById() {
        linkService.deleteLinkById(15);
    }

    @Test
    void deleteLinkByIds() {
    }

    @Test
    void updateOrderNum() {
        linkService.updateOrderNum(17, 6, "friend");
    }
}