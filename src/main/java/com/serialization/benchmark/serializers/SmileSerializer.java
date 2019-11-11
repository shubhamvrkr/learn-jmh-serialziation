package com.serialization.benchmark.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;

import java.io.IOException;

/**
 * This is an implementation of Smile serializer
 * Binary JSON
 *
 * Website: https://github.com/FasterXML/jackson-dataformats-binary/tree/master/smile
 */
public class SmileSerializer implements Serializer<byte[], Object> {

    ObjectMapper mapper;

    public SmileSerializer() {
        this.mapper = new ObjectMapper(new SmileFactory());
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] serializedObj, Class clazz) {
        try {
            return mapper.readValue(serializedObj, clazz);
        } catch (JsonProcessingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
