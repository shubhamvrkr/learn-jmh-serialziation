package com.serialization.benchmark.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is an implementation of GSON serializer.
 * JSON serializer
 *
 * Website: https://github.com/google/gson
 */
public class GsonSerializer implements Serializer<String, Object> {

    Gson gson;

    public GsonSerializer() {
        gson = new GsonBuilder()
                .disableHtmlEscaping().create();
    }

    @Override
    public String serialize(Object obj) {
        return gson.toJson(obj);
    }

    @Override
    public Object deserialize(String obj, Class clazz) {
        return gson.fromJson(obj, clazz);
    }
}
