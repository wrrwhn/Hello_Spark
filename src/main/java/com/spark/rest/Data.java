package com.spark.rest;

import java.util.LinkedList;
import java.util.List;

@lombok.Data
public class Data implements IValidable {

    private int id;
    private String title;
    private String content;
    private List categories = new LinkedList<>();

    @Override
    public boolean isValid() {
        return null != title && !title.isEmpty() && !categories.isEmpty();
    }
}
