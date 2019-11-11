package com.serialization.benchmark;

import com.serialization.benchmark.models.DocumentReference;
import com.serialization.benchmark.models.LineItem;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.models.protobuf.PurchaseOrderProtos;
import com.serialization.benchmark.serializers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class BenchmarkTest {

    PurchaseOrder purchaseOrder;
    com.serialization.benchmark.models.thrifmodels.PurchaseOrder thriftPurchaseOrder;
    PurchaseOrderProtos.PurchaseOrder protobufPurchaseOrder;

    @Before
    public void setUp() {
        purchaseOrder = GlobalModels.createTestModel();
        thriftPurchaseOrder = GlobalModels.createThriftTestModel();
        protobufPurchaseOrder = GlobalModels.createProtobufTestModel();
    }

    @Test
    public void BoonSerializationTest() {

        BoonSerializer boonSerializer = new BoonSerializer();
        String serializedJSONString = boonSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) boonSerializer.deserialize(serializedJSONString, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void DslSerializationTest() {

        DslSerializer dslSerializer = new DslSerializer();
        byte[] serializedBytes = dslSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) dslSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void FastJsonSerializationTest() {

        FastJsonSerializer fastJsonSerializer = new FastJsonSerializer();
        String serializedJSONString = fastJsonSerializer.serialize(purchaseOrder);
        System.out.println(serializedJSONString);
        PurchaseOrder actual = (PurchaseOrder) fastJsonSerializer.deserialize(serializedJSONString, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void FSTBinarySerializationTest() {

        FSTBinarySerializer fstBinarySerializer = new FSTBinarySerializer();
        byte[] serializedBytes = fstBinarySerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) fstBinarySerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void FSTJsonSerializationTest() {

        FSTJsonSerializer fstJsonSerializer = new FSTJsonSerializer();
        String serializedJSONString = fstJsonSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) fstJsonSerializer.deserialize(serializedJSONString, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void GSONSerializationTest() {

        GsonSerializer gsonSerializer = new GsonSerializer();
        String serializedJSONString = gsonSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) gsonSerializer.deserialize(serializedJSONString, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void JacksonSerializationTest() {

        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        String serializedJSONString = jacksonSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) jacksonSerializer.deserialize(serializedJSONString, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void JavaDefaultSerializationTest() {

        JavaDefaultSerializer javaDefaultSerializer = new JavaDefaultSerializer();
        byte[] serializedBytes = javaDefaultSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) javaDefaultSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void KyroSerializationTest() {

        KyroSerializer kyroSerializer = new KyroSerializer(PurchaseOrder.class, DocumentReference.class, LineItem.class,
                ArrayList.class, long[].class);
        byte[] serializedBytes = kyroSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) kyroSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void MsgPackSerializationTest() {

        //the classes needs to added from leaves to root
        MsgPackSerializer msgPackSerializer = new MsgPackSerializer(LineItem.class,
                DocumentReference.class, PurchaseOrder.class);
        byte[] serializedBytes = msgPackSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) msgPackSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }


    @Test
    public void ProtoBufSerializationTest() throws IOException {


        ProtobufSerializer protobufSerializer = new ProtobufSerializer<>(PurchaseOrderProtos.PurchaseOrder.parser());
        byte[] serializedBytes = protobufSerializer.serialize(protobufPurchaseOrder);
        PurchaseOrderProtos.PurchaseOrder actual = (PurchaseOrderProtos.PurchaseOrder) protobufSerializer.deserialize(serializedBytes, PurchaseOrderProtos.PurchaseOrder.class);
        Assert.assertEquals(protobufPurchaseOrder, actual);
    }

    @Test
    public void ProtoStuffSerializationTest() {

        ProtoStuffSerializer protoStuffSerializer = new ProtoStuffSerializer();
        byte[] serializedBytes = protoStuffSerializer.serialize(purchaseOrder);
        PurchaseOrder actual = (PurchaseOrder) protoStuffSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void SmileSerializationTest() {

        SmileSerializer smileSerializer = new SmileSerializer();
        byte[] serializedBytes = smileSerializer.serialize(purchaseOrder);
        System.out.println(serializedBytes);
        PurchaseOrder actual = (PurchaseOrder) smileSerializer.deserialize(serializedBytes, PurchaseOrder.class);
        Assert.assertEquals(purchaseOrder, actual);
    }

    @Test
    public void ThriftBinarySerializationTest() {

        ThriftBinarySerializer thriftBinarySerializer = new ThriftBinarySerializer();
        byte[] serializedBytes = thriftBinarySerializer.serialize(thriftPurchaseOrder);
        com.serialization.benchmark.models.thrifmodels.PurchaseOrder actual =
                (com.serialization.benchmark.models.thrifmodels.PurchaseOrder) thriftBinarySerializer.deserialize(serializedBytes, com.serialization.benchmark.models.thrifmodels.PurchaseOrder.class);
        Assert.assertEquals(thriftPurchaseOrder, actual);
    }

    @Test
    public void ThriftJsonSerializationTest() {

        ThriftJsonSerializer thriftJsonSerializer = new ThriftJsonSerializer();
        String serializedBytes = thriftJsonSerializer.serialize(thriftPurchaseOrder);
        com.serialization.benchmark.models.thrifmodels.PurchaseOrder actual =
                (com.serialization.benchmark.models.thrifmodels.PurchaseOrder) thriftJsonSerializer.deserialize(serializedBytes, com.serialization.benchmark.models.thrifmodels.PurchaseOrder.class);
        Assert.assertEquals(thriftPurchaseOrder, actual);
    }


}
