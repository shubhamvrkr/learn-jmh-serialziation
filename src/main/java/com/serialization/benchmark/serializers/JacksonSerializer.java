package com.serialization.benchmark.serializers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is an implementation of Jackson 2 serializer.
 * JSON serializer
 * JSON string can be read or write as generic JsonNode.
 *
 * Website: https://github.com/FasterXML/jackson-databind
 */
public class JacksonSerializer implements Serializer<String, Object> {

    ObjectMapper mapper;

    public JacksonSerializer() {
        this.mapper = new ObjectMapper();
    }

    /**
     * Serialize the object to JSON string
     *
     * @param obj
     * @return
     */
    @Override
    public String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {

            return null;
        }
    }

    /**
     * Deserialize the JSON string to object
     *
     * @param serializedObj
     * @param clazz
     * @return instance of clazz
     */
    @Override
    public Object deserialize(String serializedObj, Class clazz) {
        try {
            return mapper.readValue(serializedObj, clazz);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
