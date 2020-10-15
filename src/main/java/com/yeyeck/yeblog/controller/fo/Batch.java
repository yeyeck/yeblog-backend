package com.yeyeck.yeblog.controller.fo;

import lombok.Data;

import java.util.List;

@Data
public class Batch<T> {

    private List<T> objects;
}
