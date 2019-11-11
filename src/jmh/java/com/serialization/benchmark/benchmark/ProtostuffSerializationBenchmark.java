package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.ProtoStuffSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ProtostuffSerializationBenchmark {


    @Benchmark
    public void protostuffSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.protoStuffSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void protostuffDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.protoStuffSerializer.deserialize(serializedBytes,
                PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        ProtoStuffSerializer protoStuffSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            protoStuffSerializer = new ProtoStuffSerializer();
            serializedBytes = protoStuffSerializer.serialize(purchaseOrder);
        }

    }
}
