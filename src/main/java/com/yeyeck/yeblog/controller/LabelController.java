package com.yeyeck.yeblog.controller;

import com.yeyeck.yeblog.controller.fo.LabelFo;
import com.yeyeck.yeblog.pojo.Label;
import com.yeyeck.yeblog.service.ILabelService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/label")
public class LabelController {

    private ILabelService labelService;

    public LabelController(ILabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("")
    @RequiresRoles("admin")
    public Label addLabel(@Validated @RequestBody LabelFo labelFo) {
        return labelService.addLabel(labelFo.getName());
    }
}
