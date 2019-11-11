package com.serialization.benchmark.serializers;


import java.io.*;

/**
 * This is an implementation of java default serializer
 * Binary serializer
 *
 */
public class JavaDefaultSerializer implements Serializer<byte[], Object> {

    @Override
    public byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(stream);
            outputStream.writeObject(obj);
            outputStream.flush();
            outputStream.close();
            return stream.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(obj);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
