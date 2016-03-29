package com.spark.test.handler.impl;

import com.spark.test.handler.AbstractRequestHandler;
import com.spark.test.handler.Answer;
import com.spark.test.model.Empty;
import com.spark.test.service.Model;

import java.util.Map;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;

public class ArticleListHandler extends AbstractRequestHandler<Empty> {

    public ArticleListHandler(Model model) {
        super(Empty.class, model);
    }

    @Override
    protected Answer processImpl(Empty value, Map urlParams, boolean shouldReturnHtml) {
        if (shouldReturnHtml) {
            String html = body().with(
                    h1("My wonderful blog"),
                    div().with(
                            model.list().stream().map((p) ->
                                    div().with(
                                            h2(p.getTitle()),
                                            p(p.getContent()),
                                            ul().with(p.getCategories().stream().map((cat) ->
                                                    li(cat))
                                                    .collect(Collectors.toList()))))
                                    .collect(Collectors.toList()))
            ).render();
            return Answer.ok(html);
        } else {
            String json = dataToJson(model.list());
            return Answer.ok(json);
        }
    }

}
