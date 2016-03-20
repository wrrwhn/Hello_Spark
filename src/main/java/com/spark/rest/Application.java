package com.spark.rest;

import static spark.Spark.get;

/**
 * Created by Yao on 2016/3/18.
 */
public class Application {

    public static void main(String[] args) {

        get("/hello", (req, res)-> "hello world");
    }
}
