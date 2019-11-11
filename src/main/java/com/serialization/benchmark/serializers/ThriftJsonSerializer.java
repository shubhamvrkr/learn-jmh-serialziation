package com.serialization.benchmark.serializers;

import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TSimpleJSONProtocol;

/**
 * This is an implementation of Thrift JSON serializer
 * JSON serializer. see {@link ThriftBinarySerializer} for binary serializer.
 *
 * Website: https://github.com/apache/thrift
 */
public class ThriftJsonSerializer implements Serializer<String, TBase> {

    TSerializer serializer;
    TDeserializer deserializer;

    public ThriftJsonSerializer() {
        serializer = new TSerializer(new  TJSONProtocol.Factory());
        deserializer = new TDeserializer(new TJSONProtocol.Factory());
    }

    @Override
    public String serialize(Object obj) {
        try {
            return serializer.toString((TBase) obj);
        } catch (TException e) {
            return null;
        }
    }

    @Override
    public TBase deserialize(String serBytes, Class clazz) {
        try {
            TBase obj = (TBase) clazz.newInstance();
            deserializer.fromString(obj, serBytes);
            return obj;
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        } catch (TException e) {
            return null;
        }
    }
}
