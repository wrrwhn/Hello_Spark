package com.spark.test.handler.impl;

import com.spark.test.handler.*;
import com.spark.test.model.Article;
import com.spark.test.service.Model;

import java.util.Map;
import java.util.UUID;

public class ArticleCreaterHandler extends AbstractRequestHandler<Article> {

    private Model model;

    public ArticleCreaterHandler(Model model) {
        super(Article.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(Article article, Map<String, String> urlParams, boolean shouldReturnHtml) {
        UUID id = model.add(article);
        return new Answer(200, id.toString());
    }
}
