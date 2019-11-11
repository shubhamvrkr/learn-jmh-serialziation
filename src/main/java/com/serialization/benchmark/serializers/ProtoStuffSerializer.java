package com.serialization.benchmark.serializers;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;

/**
 * This is an implementation of protostuff serializer
 * Binary serializer
 * Flexible Schemas are generated at runtime or compile time
 * For existing objects, use protostuff-runtime which uses reflection.
 *
 * Website: https://github.com/protostuff/protostuff
 */
public class ProtoStuffSerializer implements Serializer<byte[], Object> {

    static final DefaultIdStrategy STRATEGY = new DefaultIdStrategy(IdStrategy.DEFAULT_FLAGS |
            IdStrategy.ALLOW_NULL_ARRAY_ELEMENT);

    @Override
    public byte[] serialize(Object obj) {
        final byte[] protostuff;
        Schema schema = RuntimeSchema.getSchema(obj.getClass(), STRATEGY);
        LinkedBuffer buffer = LinkedBuffer.allocate();
        protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        buffer.clear();
        return protostuff;
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        Schema schema = RuntimeSchema.getSchema(clazz, STRATEGY);
        Object resp = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(obj, resp, schema);
        return resp;
    }
}
