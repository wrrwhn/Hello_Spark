package com.spark.template;

import com.spark.sql.DataService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.eclipse.jetty.http.HttpStatus;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class Application {

    public static void main(String[] args) {

        // 连接至数据库
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/spark", "postgres", "8P$tZiCTek%u");
        DataService service = new DataService(sql2o);

        // freemarker conf
        FreeMarkerEngine engine = new FreeMarkerEngine();
        Configuration conf = new Configuration();
        conf.setTemplateLoader(new ClassTemplateLoader(Application.class, "/template"));
        engine.setConfiguration(conf);

        get("/hello", (req, res) -> {
            if (isHtml(req)) {
                res.status(HttpStatus.OK_200);
                res.type("text/html");
                Map map = new HashMap();
                map.put("list", service.list());
                return engine.render(new ModelAndView(map, "freemarker.ftl"));
            } else {
                return service.dataToJson(service.list());
            }
        });
    }

    // check the req header
    private static boolean isHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }
}
