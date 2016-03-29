package com.spark.test.service;

import com.spark.test.model.Article;

import java.util.List;
import java.util.UUID;

public interface Model {

    UUID add(Article article);

    List<Article> list();
}
