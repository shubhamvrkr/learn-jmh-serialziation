package com.serialization.benchmark.serializers;

public interface Serializer<T, U> {

    //abstract method to serialize the object
    T serialize(Object obj);

    //abstract method to deserialize the object
    U deserialize(T obj, Class clazz);
}