package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.JavaDefaultSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JavaSerializationBenchmark {

    @Benchmark
    public void javaSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.javaDefaultSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void javaDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
       // byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.javaDefaultSerializer.deserialize(serializationAttributes.serializedBytes,
                PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        JavaDefaultSerializer javaDefaultSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            javaDefaultSerializer = new JavaDefaultSerializer();
            serializedBytes = javaDefaultSerializer.serialize(purchaseOrder);
        }

    }

}
