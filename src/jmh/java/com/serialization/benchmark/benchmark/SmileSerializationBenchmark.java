package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.SmileSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SmileSerializationBenchmark {


    @Benchmark
    public void smileSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.smileSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }


    @Benchmark
    public void smileDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.smileSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        SmileSerializer smileSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            smileSerializer = new SmileSerializer();
            serializedBytes = smileSerializer.serialize(purchaseOrder);
        }

    }
}
