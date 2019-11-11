package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.DocumentReference;
import com.serialization.benchmark.models.LineItem;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.KyroSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class KyroSerializationBenchmark {

    @Benchmark
    public void kyroSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.kyroSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void kyroDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.kyroSerializer.deserialize(serializedBytes,
                PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        KyroSerializer kyroSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            kyroSerializer = new KyroSerializer(PurchaseOrder.class, DocumentReference.class, LineItem.class,
                    ArrayList.class, long[].class);
            serializedBytes = kyroSerializer.serialize(purchaseOrder);
        }

    }

}
