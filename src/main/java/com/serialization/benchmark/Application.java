package com.serialization.benchmark;
import com.serialization.benchmark.models.DocumentReference;
import com.serialization.benchmark.models.LineItem;
import com.serialization.benchmark.models.PurchaseOrder;
import com.serialization.benchmark.models.protobuf.PurchaseOrderProtos;
import com.serialization.benchmark.serializers.*;

import java.io.*;
import java.util.ArrayList;

public class Application {

    final static int EXECUTION_ITERATION = 100000;
    final static int WARMUP_ITERATION = 100000;


    static PurchaseOrder testModel = GlobalModels.createTestModel();
    static com.serialization.benchmark.models.thrifmodels.PurchaseOrder thriftTestModel = GlobalModels.createThriftTestModel();
    static PurchaseOrderProtos.PurchaseOrder protobufTestModel = GlobalModels.createProtobufTestModel();

    public static void main(String[] args) throws Exception{

        String serializedJson;
        byte[] serializedBytes;
        Result serializedResult;
        Result deserializationResult;

        System.out.println("******************************************************************************************************************************************************************************");
        System.out.format("%-70s%-30s%-30s%-30s%-30s\n", "Serializer Name", "Serialize Time(ns)","Deserialize Time(ns)","Serialize Size(b)","Deserialize Size(b)");
        System.out.println("******************************************************************************************************************************************************************************");

        /**
         * Evaluate performance and size of boon serializer
         */
        BoonSerializer boonSerializer = new BoonSerializer();
        serializedJson = boonSerializer.serialize(testModel);
        warmUpSerialization(boonSerializer,testModel,false);
        serializedResult = evaluateSerialization(boonSerializer,testModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(boonSerializer,serializedJson,testModel.getClass());
        deserializationResult = evaluateDeserialization(boonSerializer,serializedJson,testModel.getClass());
        print(boonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of dsl serializer/deserializer
         */
        DslSerializer dslSerializer = new DslSerializer();
        serializedBytes = dslSerializer.serialize(testModel);
        warmUpSerialization(dslSerializer,testModel,false);
        serializedResult = evaluateSerialization(dslSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(dslSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(dslSerializer,serializedBytes,testModel.getClass());
        print(dslSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of fast json serializer/deserializer
         */
        FastJsonSerializer fastJsonSerializer = new FastJsonSerializer();
        serializedJson = fastJsonSerializer.serialize(testModel);
        warmUpSerialization(fastJsonSerializer,testModel,false);
        serializedResult = evaluateSerialization(fastJsonSerializer,testModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(fastJsonSerializer,serializedJson,testModel.getClass());
        deserializationResult = evaluateDeserialization(fastJsonSerializer,serializedJson,testModel.getClass());
        print(fastJsonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of fst binary serializer/deserializer
         */
        FSTBinarySerializer fstBinarySerializer = new FSTBinarySerializer();
        serializedBytes = fstBinarySerializer.serialize(testModel);
        warmUpSerialization(fstBinarySerializer,testModel,false);
        serializedResult = evaluateSerialization(fstBinarySerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(fstBinarySerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(fstBinarySerializer,serializedBytes,testModel.getClass());
        print(fstBinarySerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);


        /**
         * Evaluate performance and size of fst json serializer/deserializer
         */
        FSTJsonSerializer fstJsonSerializer = new FSTJsonSerializer();
        serializedJson = fstJsonSerializer.serialize(testModel);
        warmUpSerialization(fstJsonSerializer,testModel,false);
        serializedResult = evaluateSerialization(fstJsonSerializer,testModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(fstJsonSerializer,serializedJson,testModel.getClass());
        deserializationResult = evaluateDeserialization(fstJsonSerializer,serializedJson,testModel.getClass());
        print(fstJsonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of gson serializer/deserializer
         */
        GsonSerializer gsonSerializer = new GsonSerializer();
        serializedJson = gsonSerializer.serialize(testModel);
        warmUpSerialization(gsonSerializer,testModel,false);
        serializedResult = evaluateSerialization(gsonSerializer,testModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(gsonSerializer,serializedJson,testModel.getClass());
        deserializationResult = evaluateDeserialization(gsonSerializer,serializedJson,testModel.getClass());
        print(gsonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of jackson serializer/deserializer
         */
        JacksonSerializer jacksonSerializer = new JacksonSerializer();
        serializedJson = jacksonSerializer.serialize(testModel);
        warmUpSerialization(jacksonSerializer,testModel,false);
        serializedResult = evaluateSerialization(jacksonSerializer,testModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(jacksonSerializer,serializedJson,testModel.getClass());
        deserializationResult = evaluateDeserialization(jacksonSerializer,serializedJson,testModel.getClass());
        print(jacksonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of java default serializer/deserializer
         */
        JavaDefaultSerializer javaDefaultSerializer = new JavaDefaultSerializer();
        serializedBytes = javaDefaultSerializer.serialize(testModel);
        warmUpSerialization(javaDefaultSerializer,testModel,false);
        serializedResult = evaluateSerialization(javaDefaultSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(javaDefaultSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(javaDefaultSerializer,serializedBytes,testModel.getClass());
        print(javaDefaultSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of kyro serializer/deserializer
         *
         */
        KyroSerializer kyroSerializer = new KyroSerializer(PurchaseOrder.class, DocumentReference.class, LineItem.class,
                ArrayList.class, long[].class);
        serializedBytes = kyroSerializer.serialize(testModel);
        warmUpSerialization(kyroSerializer,testModel,false);
        serializedResult = evaluateSerialization(kyroSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(kyroSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(kyroSerializer,serializedBytes,testModel.getClass());
        print(kyroSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of msg pack serializer/deserializer
         */
        MsgPackSerializer msgPackSerializer = new MsgPackSerializer(LineItem.class,
                DocumentReference.class, PurchaseOrder.class);
        serializedBytes = msgPackSerializer.serialize(testModel);
        warmUpSerialization(msgPackSerializer,testModel,false);
        serializedResult = evaluateSerialization(msgPackSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(msgPackSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(msgPackSerializer,serializedBytes,testModel.getClass());
        print(msgPackSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of protobuf serializer/deserializer
         */
        ProtobufSerializer protobufSerializer = new ProtobufSerializer<>(PurchaseOrderProtos.PurchaseOrder.parser());
        serializedBytes = protobufSerializer.serialize(protobufTestModel);
        warmUpSerialization(protobufSerializer,protobufTestModel,false);
        serializedResult = evaluateSerialization(protobufSerializer,protobufTestModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(protobufSerializer,serializedBytes,protobufTestModel.getClass());
        deserializationResult = evaluateDeserialization(protobufSerializer,serializedBytes,protobufTestModel.getClass());
        print(protobufSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);


        /**
         * Evaluate performance and size of protostuff serializer/deserializer
         */
        ProtoStuffSerializer protoStuffSerializer = new ProtoStuffSerializer();
        serializedBytes = protoStuffSerializer.serialize(testModel);
        warmUpSerialization(protoStuffSerializer,testModel,false);
        serializedResult = evaluateSerialization(protoStuffSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(protoStuffSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(protoStuffSerializer,serializedBytes,testModel.getClass());
        print(protoStuffSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of smile serializer/deserializer
         */
        SmileSerializer smileSerializer = new SmileSerializer();
        serializedBytes = smileSerializer.serialize(testModel);
        warmUpSerialization(smileSerializer,testModel,false);
        serializedResult = evaluateSerialization(smileSerializer,testModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(smileSerializer,serializedBytes,testModel.getClass());
        deserializationResult = evaluateDeserialization(smileSerializer,serializedBytes,testModel.getClass());
        print(smileSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

        /**
         * Evaluate performance and size of thrift binary serializer/deserializer
         */
        ThriftBinarySerializer thriftBinarySerializer = new ThriftBinarySerializer();
        serializedBytes = thriftBinarySerializer.serialize(thriftTestModel);
        warmUpSerialization(thriftBinarySerializer,thriftTestModel,false);
        serializedResult = evaluateSerialization(thriftBinarySerializer,thriftTestModel,false);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(thriftBinarySerializer,serializedBytes,thriftTestModel.getClass());
        deserializationResult = evaluateDeserialization(thriftBinarySerializer,serializedBytes,thriftTestModel.getClass());
        print(thriftBinarySerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);


        /**
         * Evaluate performance and size of thrift json serializer/deserializer
         */
        ThriftJsonSerializer thriftJsonSerializer = new ThriftJsonSerializer();
        serializedJson = thriftJsonSerializer.serialize(thriftTestModel);
        warmUpSerialization(thriftJsonSerializer,thriftTestModel,false);
        serializedResult = evaluateSerialization(thriftJsonSerializer,thriftTestModel,true);
        System.gc();
        Thread.sleep(2000);
        warmupDeserializationDeserialization(thriftJsonSerializer,serializedJson,thriftTestModel.getClass());
        deserializationResult = evaluateDeserialization(thriftJsonSerializer,serializedJson,thriftTestModel.getClass());
        print(thriftJsonSerializer.getClass().getName(),serializedResult,deserializationResult);
        System.gc();
        Thread.sleep(2000);

    }



    //print serialization and deserialization result
    static void print(String serializerName, Result serializationResult, Result deserializationResult){

        System.out.format("%-70s%-30s%-30s%-30s%-30s\n", serializerName, String.valueOf(serializationResult.responseTime),
                String.valueOf(deserializationResult.responseTime),String.valueOf(serializationResult.size),String.valueOf(deserializationResult.size));

    }

    //run serialization code
    static Result evaluateSerialization(Serializer serializer, Object object, boolean isJson){
        long[] responseTime = new long[EXECUTION_ITERATION];
        int[] serializedSize = new int[EXECUTION_ITERATION];

        //perform ITERATIONS times serialized operations and obtain response time and size of serialized data
        for(int i=0;i<EXECUTION_ITERATION;i++){
            long startTime = System.nanoTime();
            Object response = serializer.serialize(object);
            long endTime = System.nanoTime();
            responseTime[i] = endTime - startTime;
            if(isJson){
                serializedSize[i] = ((String)response).getBytes().length;
            }else{
                serializedSize[i] = ((byte[])response).length;
            }
        }
        return calculateStatistics(responseTime,serializedSize);

    }

    //run serialization code
    static Result evaluateDeserialization(Serializer serializer, Object object,Class clazz){
        long[] responseTime = new long[EXECUTION_ITERATION];
        int[] serializedSize = new int[EXECUTION_ITERATION];

        //perform ITERATIONS times serialized operations and obtain response time and size of serialized data
        for(int i=0;i<EXECUTION_ITERATION;i++){
            long startTime = System.nanoTime();
            Object response = serializer.deserialize(object,clazz);
            long endTime = System.nanoTime();
            responseTime[i] = endTime - startTime;
            serializedSize[i] = getObjectBytes(response).length;
        }
        return calculateStatistics(responseTime,serializedSize);

    }

    //get bytes from object
    static byte[] getObjectBytes(Object object) {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        }catch (IOException e){
            return new byte[0];
        }
    }

    //calculate average
    static Result calculateStatistics(long[] responseTime, int[] serializedSize){

        long totalTime = 0;
        long totalSize = 0;
        //calculate average response time and size
        for(int i=0;i<EXECUTION_ITERATION;i++){
            totalTime+= responseTime[i];
        }
        //calculate average response time and size
        for(int i=0;i<EXECUTION_ITERATION;i++){
            totalSize+= serializedSize[i];
        }
        return new Result(totalTime/EXECUTION_ITERATION,totalSize/EXECUTION_ITERATION);
    }

    //run serialization code
    static Object warmUpSerialization(Serializer serializer, Object object, boolean isJson){

        Object obj = null;
        for(int i=0;i<WARMUP_ITERATION;i++){
            obj = serializer.serialize(object);

        }
        return obj;
    }

    //run serialization code
    static Object warmupDeserializationDeserialization(Serializer serializer, Object object,Class clazz){

        Object obj = null;
        //perform ITERATIONS times serialized operations and obtain response time and size of serialized data
        for(int i=0;i<WARMUP_ITERATION;i++){
            obj = serializer.deserialize(object,clazz);
        }
        return obj;
    }


    //holder for result
    static class Result {

        double responseTime;
        double size;

        public Result(double responsetime, double size) {
            this.responseTime = responsetime;
            this.size = size;
        }
    }


}
