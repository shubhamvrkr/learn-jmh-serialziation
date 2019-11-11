package com.serialization.benchmark.serializers;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

/**
 * This is an implementation of boon serializer.
 * JSON serializer
 * Boon works with lists, arrays, sets, maps, sorted maps, etc.
 * TODO - Boon JSON parser faster than jackson and gson - validate
 * Website: https://github.com/boonproject/boon
 *
 */
public class BoonSerializer implements Serializer<String, Object> {
    ObjectMapper mapper;

    public BoonSerializer() {
        mapper = JsonFactory.create();
    }

    @Override
    public String serialize(Object obj) {
        return mapper.toJson(obj);
    }

    @Override
    public Object deserialize(String obj, Class clazz) {
        return mapper.fromJson(obj, clazz);
    }
}
