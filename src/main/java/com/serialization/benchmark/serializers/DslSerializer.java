package com.serialization.benchmark.serializers;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * This is an implementation of DLS-JSON-Java8 serializer
 * JSON serializer that works on byte level
 *
 * Website: https://github.com/ngs-doo/dsl-json
 */
public class DslSerializer implements Serializer<byte[], Object> {

    DslJson<Object> dslJson;

    public DslSerializer() {
        dslJson = new DslJson<>(Settings.basicSetup());
    }

    @Override
    public byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            dslJson.serialize(obj, os);
            return os.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Object deserialize(byte[] obj, Class clazz) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(obj);
            return dslJson.deserialize(clazz, is);
        } catch (IOException e) {
            return null;
        }
    }
}
