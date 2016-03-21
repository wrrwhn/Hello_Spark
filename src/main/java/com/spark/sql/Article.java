package com.spark.sql;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@lombok.Data
public class Article implements IValidable {

    private UUID id;
    private String title;
    private String content;
    private List<Category> categories = new LinkedList<>();

    @Override
    public boolean isValid() {
        return null != title && !title.isEmpty() && !categories.isEmpty();
    }
}
