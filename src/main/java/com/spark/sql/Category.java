package com.spark.sql;

import java.util.UUID;

@lombok.Data
public class Category {

    private UUID id;
    private UUID article_id;
    private String content;
}
