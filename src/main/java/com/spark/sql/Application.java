package com.spark.sql;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2o;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Yao on 2016/3/18.
 */
public class Application {

    public static void main(String[] args) {

        // 连接至数据库
        Sql2o sql2o= new Sql2o("jdbc:postgresql://localhost:5432/spark", "postgres", "8P$tZiCTek%u");
        DataService service = new DataService(sql2o);

        post("/add", (req, res)->{
           try{
               ObjectMapper mapper= new ObjectMapper();
               Article data;
               data = mapper.readValue(req.body(), Article.class);
               if(!data.isValid()){
                   res.status(HttpStatus.BAD_REQUEST_400);
                   return null;
               }

               res.status(HttpStatus.OK_200);
               return service.add(data);
           }catch (JsonParseException ex){
                res.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
               return null;
           }
        });

        get("/list", (req, res)->{
            res.status(HttpStatus.OK_200);
            res.type("application/json");
            return service.dataToJson(service.list());
        });
    }
}
