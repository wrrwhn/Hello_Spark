package com.spark.sql;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {

    private UUID id;
    private UUID article_id;
    private String content;
}
