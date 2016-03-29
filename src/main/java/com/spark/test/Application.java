package com.spark.test;

import com.spark.test.handler.impl.ArticleCreaterHandler;
import com.spark.test.handler.impl.ArticleListHandler;
import com.spark.test.service.Sql2oModel;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Yao on 2016/3/18.
 */
public class Application {

    public static void main(String[] args) {

        // 连接至数据库
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/spark", "postgres", "8P$tZiCTek%u");
        Sql2oModel model = new Sql2oModel(sql2o);

        post("/add", new ArticleCreaterHandler(model));
        get("/list", new ArticleListHandler(model));
    }
}
