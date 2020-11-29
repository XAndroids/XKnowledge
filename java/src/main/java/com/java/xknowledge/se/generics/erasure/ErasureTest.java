package com.java.xknowledge.se.generics.erasure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 泛型擦除
 * 1.无限制类型擦除，为Object类型；
 * 2.有限制类型擦除，为上界类型；
 * 3.泛型方法擦除，为上界类型；
 * 参考：https://www.bilibili.com/video/BV1xJ411n77R?p=11
 */
class ErasureTest<T, K extends Number> {
    private T key;//无限制类型泛型，
    private K value;//有限制类型泛型

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getInfo() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    //泛型方法类型
    public <V extends Number> V getInfo(V info) {
        return info;
    }

    public static void main(String[] args) {
        ErasureTest<Integer, Number> integerErasureTest = new ErasureTest<>();
        Class<? extends ErasureTest> clz = integerErasureTest.getClass();
        Field[] declaredField = clz.getDeclaredFields();
        //类型擦除后，key无限制类型擦除为Object，value为有限制类型擦除为上界
        //key:Object
        //value:Number
        for (Field field : declaredField) {
            System.out.println(field.getName() + ":" + field.getType().getSimpleName());
        }
        System.out.println();
        Method[] declaredMethod = clz.getMethods();
        //泛型方法擦除为上界
        //getInfo:Number
        //getInfo:Number
        for (Method method : declaredMethod) {
            System.out.println(method.getName() + ":" + method.getReturnType().getSimpleName());
        }
    }
}
