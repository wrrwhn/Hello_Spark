package com.spark.test.model;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@lombok.Data
public class Category implements Validable {

    private UUID id;
    private UUID article_id;
    private String content;

    @Override
    public boolean isValid() {
        return null != this.getArticle_id() && StringUtils.isNotBlank(this.getContent());
    }
}
