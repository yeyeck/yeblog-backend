package com.yeyeck.yeblog.controller.fo;

import com.yeyeck.yeblog.pojo.Link;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LinkFo {

    @NotBlank
    private String label;

    @NotBlank
    private String link;

    @NotBlank
    private String type;

    private Boolean newBlank = false;

    public Link toLink() {
        Link link = new Link();
        link.setLabel(this.label);
        link.setType(this.type);
        link.setLink(this.link);
        link.setNewBlank(this.newBlank);
        return link;
    }
}
