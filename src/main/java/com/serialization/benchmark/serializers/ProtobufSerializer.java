package com.serialization.benchmark.serializers;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This is an implementation of google protobuf serializer
 * Binary serializer
 * Need classes protobuf classes at compile time.
 *
 * Website: https://developers.google.com/protocol-buffers/docs/javatutorial
 * @param <T>
 */
public class ProtobufSerializer<T extends Message> implements Serializer<byte[], Object> {

    private Parser<T> parser;

    public ProtobufSerializer(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GeneratedMessageV3 object = (GeneratedMessageV3) obj;
        try {
            object.writeTo(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        try {
            return parser.parseFrom(obj);
        } catch (InvalidProtocolBufferException e) {
            return null;
        }

    }
}
