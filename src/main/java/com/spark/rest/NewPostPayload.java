package com.spark.rest;

import java.util.List;

/**
 * Creator: Yao
 * Date:    2016/3/20
 * For:
 * Other:
 */
public class NewPostPayload {

    private String title;
    private List categories;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List getCategories() {
        return categories;
    }

    public void setCategories(List categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
