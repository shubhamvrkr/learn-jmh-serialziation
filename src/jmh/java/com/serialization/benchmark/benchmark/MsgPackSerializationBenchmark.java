package com.serialization.benchmark.benchmark;

import com.serialization.benchmark.GlobalModels;
import com.serialization.benchmark.models.DocumentReference;
import com.serialization.benchmark.models.LineItem;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.serializers.MsgPackSerializer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Fork(value = 3, jvmArgs = "-XX:+PrintCompilation -verbose:gc")
@Warmup(iterations = 2)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MsgPackSerializationBenchmark {

    @Benchmark
    public void msgPackSerialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = serializationAttributes.msgPackSerializer.serialize(serializationAttributes.purchaseOrder);
        bh.consume(serializedBytes);
    }

    @Benchmark
    public void msgPackDeserialization(SerializationAttributes serializationAttributes, Blackhole bh) {
        byte[] serializedBytes = Arrays.copyOf(serializationAttributes.serializedBytes, serializationAttributes.serializedBytes.length);
        PurchaseOrder purchaseOrder = (PurchaseOrder) serializationAttributes.msgPackSerializer.deserialize(serializedBytes,
                PurchaseOrder.class);
        bh.consume(purchaseOrder);
    }

    @State(Scope.Thread)
    public static class SerializationAttributes {

        PurchaseOrder purchaseOrder;
        MsgPackSerializer msgPackSerializer;
        byte[] serializedBytes;

        @Setup(Level.Trial)
        public void prepare() {
            purchaseOrder = GlobalModels.createTestModel();
            msgPackSerializer = new MsgPackSerializer(LineItem.class,
                    DocumentReference.class, PurchaseOrder.class);
            serializedBytes = msgPackSerializer.serialize(purchaseOrder);
        }

    }
}
