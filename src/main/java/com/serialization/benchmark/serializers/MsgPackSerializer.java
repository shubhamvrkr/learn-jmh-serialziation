package com.serialization.benchmark.serializers;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.io.IOException;

/**
 * This is an implementation of MsgPack serializer.
 * Binary Serializer
 * Supports serialization and deserialization of Java objects through jackson-databind.
 *
 * Website: https://github.com/msgpack/msgpack-java
 */
public class MsgPackSerializer implements Serializer<byte[], Object> {

    MessagePack msgpack;

    public MsgPackSerializer(Class... classes) {
        msgpack = new MessagePack();
        for(Class clazz : classes){
            msgpack.register(clazz);
        }

    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            return msgpack.write(obj);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        try {
            Value dynamic = msgpack.read(obj);
            return msgpack.convert(dynamic, clazz);
        } catch (IOException e) {
            return null;
        }
    }
}
