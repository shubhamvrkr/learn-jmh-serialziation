package com.serialization.benchmark.serializers;

import org.nustaq.serialization.FSTConfiguration;

/**
 * This is an implementation of FST binary serializer
 * Can be configured as binary or JSON serializer. @see {@link FSTBinarySerializer} for Binary based serialization.
 * Different configuration can be plugged. check @link https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization for more option
 * Website: https://github.com/RuedigerMoeller/fast-serialization
 */
public class FSTJsonSerializer implements Serializer<String, Object> {

    private FSTConfiguration conf;

    public FSTJsonSerializer() {
        conf = FSTConfiguration.createJsonConfiguration();
    }

    @Override
    public String serialize(Object obj) {
        return conf.asJsonString(obj);
    }

    @Override
    public Object deserialize(String obj, Class clazz) {
        return conf.asObject(obj.getBytes());
    }
}
