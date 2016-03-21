package com.spark.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataService {

    static int nextId = 1;
    static Map posts = new HashMap<>();

    public int add(Data data) {
        data.setId(nextId++);
        posts.put(data.getId(), data);
        return data.getId();
    }

    public List list() {
        return (List) posts.keySet().stream().sorted().map((id) -> posts.get(id)).collect(Collectors.toList());
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
