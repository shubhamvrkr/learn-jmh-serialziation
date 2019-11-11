package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.FSTJsonSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class FSTJsonSerializationBenchmark {

    @Benchmark
    public void fstJsonSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.fstJsonSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }


    @Benchmark
    public void fstJsonDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        String serializedBytes = serializationAttributes.serializedBytes;
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.fstJsonSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }



    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        FSTJsonSerializer fstJsonSerializer;
        String serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            fstJsonSerializer = new FSTJsonSerializer();
            serializedBytes = fstJsonSerializer.serialize(purchaseOrder);
        }

    }
}
