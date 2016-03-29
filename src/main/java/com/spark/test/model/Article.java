package com.spark.test.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@lombok.Data
public class Article implements Validable {

    private UUID id;
    private String title;
    private String content;
    private List<String> categories = new LinkedList<>();

    @Override
    public boolean isValid() {
        return null != title && !title.isEmpty() && !categories.isEmpty();
    }
}
