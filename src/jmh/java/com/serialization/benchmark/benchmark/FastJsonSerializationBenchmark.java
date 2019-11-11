package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.FastJsonSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FastJsonSerializationBenchmark {


    @Benchmark
    public void fastJsonSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.fastJsonSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }


    @Benchmark
    public void fastJsonDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.serializedBytes;
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.fastJsonSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        FastJsonSerializer fastJsonSerializer;
        String serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            fastJsonSerializer = new FastJsonSerializer();
            serializedBytes = fastJsonSerializer.serialize(purchaseOrder);
        }

    }
}
