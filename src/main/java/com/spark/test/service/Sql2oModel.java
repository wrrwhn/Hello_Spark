package com.spark.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spark.test.model.Article;
import com.spark.test.model.Category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Sql2oModel implements Model {

    private Sql2o sql2o;

    public Sql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public UUID add(Article article) {
        try (Connection conn = sql2o.beginTransaction()) {
            article.setId(UUID.randomUUID());
            conn.createQuery("insert into article(id, title, content) values(:id, :title, :content)")   // 插入语句
                    .addParameter("id", article.getId())                                                // 补充参数值
                    .addParameter("title", article.getTitle())
                    .addParameter("content", article.getContent())
                    .executeUpdate();                                                                   // 语句执行
            if (null != article.getCategories() && article.getCategories().size() > 0) {
                article.getCategories().forEach(p -> {
                    conn.createQuery("insert into category(id, article_id, content) values(:id, :article_id, :content)")
                            .addParameter("id", UUID.randomUUID())
                            .addParameter("article_id", article.getId())
                            .addParameter("content", p)
                            .executeUpdate();
                });
            }
            conn.commit();                                                                              // 执行内容提交
            return article.getId();
        }

    }

    public List<Article> list() {
        try (Connection conn = sql2o.beginTransaction()) {
            List<Article> list = conn.createQuery("select * from article")
                    .executeAndFetch(Article.class);
            list.forEach(p -> {
                List<Category> categories = conn.createQuery("select * from category where article_id= :article_id")
                        .addParameter("article_id", p.getId())
                        .executeAndFetch(Category.class);                                               // 结果类型转换

                if (null != categories && categories.size() > 0)
                    p.setCategories(categories.stream().map(Category::getContent).collect(Collectors.toList()));
            });
            return list;
        }
    }

    public static String dataToJson(Object data) {

        StringWriter writer = new StringWriter();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(writer, data);
            return writer.toString();
        } catch (IOException ex) {
            throw new IllegalStateException("IOException from StringWritter");
        } finally {
            if (null != writer) {
                writer.flush();
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
