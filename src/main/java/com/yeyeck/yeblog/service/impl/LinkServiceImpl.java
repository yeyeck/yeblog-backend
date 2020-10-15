package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.constants.LinkType;
import com.yeyeck.yeblog.controller.fo.LinkFo;
import com.yeyeck.yeblog.exception.SystemLimitException;
import com.yeyeck.yeblog.mapper.LinkMapper;
import com.yeyeck.yeblog.pojo.Link;
import com.yeyeck.yeblog.service.ILinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LinkServiceImpl implements ILinkService {

    private LinkMapper linkMapper;

    public LinkServiceImpl(LinkMapper linkMapper) {
        this.linkMapper = linkMapper;
    }

    @Override
    public Link addLink(LinkFo linkFo) {
        Link link = linkFo.toLink();
        LinkType type = LinkType.valueOf(link.getType().toUpperCase());
        int count = linkMapper.countByType(link.getType());
        if (count >= type.getLimit()) {
            throw new SystemLimitException("该类型的链接不能超过" + type.getLimit() + "个！");
        }
        link.setOrderNum(count);
        linkMapper.add(link);
        return link;
    }

    @Override
    public List<Link> getLinkByType(String linkType) {
        return linkMapper.getLinksByType(linkType);
    }

    @Override
    public List<Link> getAll() {
        return linkMapper.getAll();
    }

    @Override
    public boolean updateLink(Integer id, LinkFo linkFo) {
        Link link = linkFo.toLink();
        link.setId(id);
        if (linkMapper.update(link) < 1) {
            throw new RuntimeException("数据库更新错误");
        }
        return true;
    }



    @Override
    public boolean deleteLinkById(Integer id) {
        Link link = linkMapper.getById(id);
        if (linkMapper.deleteLink(id) < 1) {
            throw new RuntimeException("删除失败，数据可能不存在，请尝试刷新查看！");
        }
        linkMapper.lowerOrderNum(link.getOrderNum(), link.getType());
        return true;
    }

    @Override
    public boolean deleteLinkByIds(List<Integer> ids) {
       ids.forEach(this::deleteLinkById);
        return true;
    }

    @Override
    @Transactional
    public boolean updateOrderNum(Integer linkId, Integer orderNum, String type) {
        Integer old = linkMapper.getOrderById(linkId);
        if (old == orderNum) {
            return true;
        }
        if (old > orderNum) {
            linkMapper.rangeUpperOrderNum(orderNum, old, type);
        } else {
            linkMapper.rangeLowerOrderNum(old, orderNum, type);
        }

        linkMapper.updateOrderNum(linkId, orderNum);
        return false;
    }
}
