package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.thrifmodels.PurchaseOrder;
import com.serialization.benchmark.serializers.ThriftBinarySerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ThriftBinarySerializationBenchmark {

    @Benchmark
    public void thriftBinarySerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.thriftBinarySerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void thriftBinaryDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        com.serialization.benchmark.models.PurchaseOrder purchaseOrder = (com.serialization.benchmark.models.PurchaseOrder) serializationAttributes.thriftBinarySerializer.deserialize(serializedBytes,
                com.serialization.benchmark.models.PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        ThriftBinarySerializer thriftBinarySerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createThriftTestModel();
            thriftBinarySerializer = new ThriftBinarySerializer();
            serializedBytes = thriftBinarySerializer.serialize(purchaseOrder);
        }

    }
}
