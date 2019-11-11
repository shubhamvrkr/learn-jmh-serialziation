package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.thrifmodels.PurchaseOrder;
import com.serialization.benchmark.serializers.ThriftJsonSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ThriftJsonSerializationBenchmark {

    @Benchmark
    public void thriftJsonSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.thriftJsonSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void thriftJsonDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.serializedBytes;
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.thriftJsonSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        ThriftJsonSerializer thriftJsonSerializer;
        String serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createThriftTestModel();
            thriftJsonSerializer = new ThriftJsonSerializer();
            serializedBytes = thriftJsonSerializer.serialize(purchaseOrder);
        }
    }
}
