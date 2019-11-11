package com.serialization.benchmark.serializers;

import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

/**
 * This is an implementation of Thrift Binary serializer
 * Binary serializer. see {@link ThriftJsonSerializer} for json serializer.
 *
 * Website: https://github.com/apache/thrift
 */
public class ThriftBinarySerializer implements Serializer<byte[], TBase> {

    TSerializer serializer;
    TDeserializer deserializer;

    public ThriftBinarySerializer() {
        serializer = new TSerializer(new TBinaryProtocol.Factory());
        deserializer = new TDeserializer(new TBinaryProtocol.Factory());
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            return serializer.serialize((TBase) obj);
        } catch (TException e) {
            return null;
        }
    }

    @Override
    public TBase deserialize(byte[] serBytes, Class clazz) {
        try {
            TBase obj = (TBase) clazz.newInstance();
            deserializer.deserialize(obj, serBytes);
            return obj;
        } catch (IllegalAccessException | InstantiationException e) {
            return null;
        } catch (TException e) {
            return null;
        }
    }
}
