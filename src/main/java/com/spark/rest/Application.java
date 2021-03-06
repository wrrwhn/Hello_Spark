package com.spark.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.post;
import static spark.Spark.get;

/**
 * Created by Yao on 2016/3/18.
 */
public class Application {

    public static void main(String[] args) {

        DataService service = new DataService();

        post("/add", (req, res)->{
           try{
               ObjectMapper mapper= new ObjectMapper();
               Data data;
               data = mapper.readValue(req.body(), Data.class);
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
