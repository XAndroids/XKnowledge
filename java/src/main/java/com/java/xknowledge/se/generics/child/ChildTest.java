package com.java.xknowledge.se.generics.child;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型子类关系:
 * 如果Foo是Bar的一个子类型，那么G是具有泛型声明的类或者接口：
 * 1.Foo[]依然是Bar[]的子类
 * 2.G<Foo>并不是G<Bar>的子类
 * 3.Foo<T>依然是Bar<T>的子类
 * 参考：《疯狂Java讲义》
 */
class ChildTest {

    //1、Integer[]是Number[]的子类
    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        //Integer[]是Number[]的子类
        //Java允许Integer[]赋值给Number[]是不安全的设计！！！
        Number[] numbers = integers;
        numbers[0] = 1;
        //因为0.5并不是Integer，运行时会引发ArrayStoreException异常
        //Exception in thread "main" java.lang.ArrayStoreException: java.lang.Double
        //	at com.java.xknowledge.se.generics.child.ChildTest.main(ChildTest.java:40)
//        numbers[1] = 0.5;
    }

    //2、List<Integer>不是List<Number>的子类
    public static void main1(String[] args) {
        //Java泛型泛型设计原则，只要编译时没有警告，就不会有ClasscastException异常
        List<Integer> integerList = new ArrayList<>();
        //Required type:List<Number> Provided:List<Integer>
//        List<Number> numbers = integerList;
    }


    //3、ClassB<T>是ClassA<T>的子类
    static class ClassA<T> {

    }

    static class ClassB<T> extends ClassA<T> {

    }

    public static void main2(String[] args) {
        //ClassA和ClassB是继承关系，ClassA<A>和ClassB<A>也是继承关系
        ClassB<String> stringClassB = new ClassB<>();
        ClassA<String> stringClassA = stringClassB;
        //Required type:ClassA<Integer> Provided: ClassB<String>
//        ClassA<Integer> integerClassA = stringClassB;
        System.out.print(stringClassA);
    }
}

