package com.serialization.benchmark.serializers;

import org.nustaq.serialization.FSTConfiguration;

/**
 * This is an implementation of FST binary serializer
 * Can be configured as binary or JSON serializer. @see {@link FSTJsonSerializer} for JSON based serialization.
 * Different configuration can be plugged. check @link https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization for more option
 * Website: https://github.com/RuedigerMoeller/fast-serialization
 */
public class FSTBinarySerializer implements Serializer<byte[], Object> {

    private FSTConfiguration conf;

    public FSTBinarySerializer() {
        conf = FSTConfiguration.createDefaultConfiguration();
    }

    @Override
    public byte[] serialize(Object obj) {
        return conf.asByteArray(obj);
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        return conf.asObject(obj);
    }
}
