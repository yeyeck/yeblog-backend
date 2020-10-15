package com.yeyeck.yeblog.service;

import com.yeyeck.yeblog.constants.LinkType;
import com.yeyeck.yeblog.controller.fo.LinkFo;
import com.yeyeck.yeblog.pojo.Link;

import java.util.List;

public interface ILinkService {
    Link addLink(LinkFo linkFo);

    List<Link> getLinkByType(String linkType);

    List<Link> getAll();

    boolean updateLink(Integer id, LinkFo linkFo);

    boolean deleteLinkById(Integer id);

    boolean deleteLinkByIds(List<Integer> ids);

    boolean updateOrderNum(Integer linkId, Integer orderNum, String type);
}
