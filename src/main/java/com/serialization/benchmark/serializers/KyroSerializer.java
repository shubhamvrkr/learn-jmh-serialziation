package com.serialization.benchmark.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * This is an implementation kyro serializer
 * Binary Object Graph Serializer
 * Kryo supports making deep and shallow copies of objects using direct assignment from one object to another.
 * Kryo supports streams, so it is trivial to use compression or encryption on all of the serialized bytes
 *
 * Website: https://github.com/EsotericSoftware/kryo
 */
public class KyroSerializer implements Serializer<byte[], Object> {

    Kryo kryo;

    public KyroSerializer(Class... clazz) {
        this.kryo = new Kryo();
        for (Class c : clazz) {
            this.kryo.register(c);
        }
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        Output output = new Output(b);
        this.kryo.writeClassAndObject(output, obj);
        output.close();
        return b.toByteArray();
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        ByteArrayInputStream b = new ByteArrayInputStream(obj);
        Input input = new Input(b);
        Object object =  kryo.readObjectOrNull(input, clazz);
        input.close();
        return object;
    }
}
