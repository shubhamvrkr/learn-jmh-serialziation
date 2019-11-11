package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.BoonSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BoonSerializationBenchmark {


    @Benchmark
    public void boonSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.boonSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void boonDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.serializedBytes;
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.boonSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }


    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        BoonSerializer boonSerializer;
        String serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            boonSerializer = new BoonSerializer();
            serializedBytes = boonSerializer.serialize(purchaseOrder);
        }

    }
}
