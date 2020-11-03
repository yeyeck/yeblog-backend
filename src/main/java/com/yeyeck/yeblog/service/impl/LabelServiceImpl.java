package com.yeyeck.yeblog.service.impl;

import com.yeyeck.yeblog.mapper.LabelMapper;
import com.yeyeck.yeblog.pojo.Label;
import com.yeyeck.yeblog.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements ILabelService {

    private LabelMapper labelMapper;

    @Autowired
    public LabelServiceImpl(LabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }

    @Override
    public Label addLabel(String name) {
        Label label = labelMapper.getByName(name);
        if (label != null) {
            return label;
        }
        label = new Label();
        label.setName(name);
        labelMapper.add(label);
        return label;
    }
}
