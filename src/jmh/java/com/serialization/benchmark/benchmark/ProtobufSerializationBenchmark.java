package com.serialization.benchmark.benchmark;


import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.protobuf.PurchaseOrderProtos;
import com.serialization.benchmark.serializers.ProtobufSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ProtobufSerializationBenchmark {

    @Benchmark
    public void protobufSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.protobufSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void protobufDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrderProtos.PurchaseOrder purchaseOrder = (PurchaseOrderProtos.PurchaseOrder) serializationAttributes.protobufSerializer.deserialize(serializedBytes,
                PurchaseOrderProtos.PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrderProtos.PurchaseOrder purchaseOrder;
        ProtobufSerializer protobufSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createProtobufTestModel();
            protobufSerializer = new ProtobufSerializer<>(PurchaseOrderProtos.PurchaseOrder.parser());
            serializedBytes = protobufSerializer.serialize(purchaseOrder);
        }

    }
}
