package com.serialization.benchmark.serializers;

import com.alibaba.fastjson.JSON;

/**
 * This is an implementation of fast json serializer
 * JSON serializer
 *
 * Website: https://github.com/alibaba/fastjson
 */
public class FastJsonSerializer implements Serializer<String, Object> {
    @Override
    public String serialize(Object obj) {
        return JSON.toJSONString(obj);
    }

    @Override
    public Object deserialize(String obj, Class clazz) {
        return JSON.parseObject(obj, clazz);
    }
}
