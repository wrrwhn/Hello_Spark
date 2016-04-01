package com.spark.init;

import spark.Spark;

/**
 * Created by Yao on 2016/3/18.
 */
public class Application {

    public static void main(String[] args) {

        Spark.port(1024);

        Spark.get("/hello", (req, res) -> {
            return "hello world";
        });
    }
}
