package com.java.xknowledge.se.generics.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型方法实践
 * 使用场景：定义类、接口时没有使用类型形参，但定义方法时想自己定义类型形参，可使用泛型方法
 * 参考：
 * 《疯狂Java讲义》泛型
 * https://blog.csdn.net/hxllhhy/article/details/80670357
 */
class MethodTest {
    //1.需求定义一个方法负责将一个Object数组的所有元素添加到一个Collection集合中
    //但是Collection<Object>不是List<String>等其它类型的父类：
    //错误: 不兼容的类型: List<String>无法转换为Collection<Object>
    static void fromArrayToCollection(Object[] a, Collection<Object> c) {
        for (Object object : a) {
            c.add(object);
        }
    }

    public static void main0(String[] args) {
        String[] strArr = {"a", "b"};
        List<String> stringList = new ArrayList<>();
        //错误: 不兼容的类型: List<String>无法转换为Collection<Object>
//        fromArrayToCollection(strArr, stringList);
    }

    //2.使用泛型方法，方法修饰符和返回值之间声明类型形参T，只能在该方法里使用
    //而接口和类定义的类型行参，可以在整他个接口和类中使用
    static <T> void fromArrayToCollection1(T[] a, Collection<T> c) {
        for (T t : a) {
            c.add(t);
        }
    }

    public static void main1(String[] args) {
        Object[] objects = new Object[10];
        Collection<Object> objectCollection = new ArrayList<>();
        //T代表Object类型
        fromArrayToCollection1(objects, objectCollection);

        String[] strings = new String[10];
        //T代表String类型
        Collection<String> stringCollection = new ArrayList<>();
        fromArrayToCollection1(strings, stringCollection);


        //2.T代表Object类型！！！传入一个String一个Object，String[] 是 Object[]的子类，故可以传入。但是反过来不行
        fromArrayToCollection1(strings, objectCollection);
        //Required type: String[] Provided: Object[]
//        fromArrayToCollection1(objects, stringCollection);
    }

    //3.和类、接口泛型不同，方法类型参数无须显示传入实际类型参数。编译器根据实参类型形参的值，推断出最直接的类
    static <T> void test(Collection<T> from, Collection<T> to) {
        for (T t : from) {
            to.add(t);
        }
    }

    //4.使用通配符，前一个集合的类型是后一个集合类型的子类即可
    static <T> void test1(Collection<? extends T> from, Collection<T> to) {
        for (T t : from) {
            to.add(t);
        }
    }

    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        //3.传入List<Object>和List<String>编译错误：
        //错误: 无法将类 MethodTest中的方法 test应用到给定类型;
        //        test(objectList, stringList);
        //        ^
        //  需要: Collection<T>,Collection<T>
        //  找到: List<Object>,List<String>
        //  原因: 推断类型不符合等式约束条件
        //    推断: String
        //    等式约束条件: String,Object
//        test(objectList, stringList);

        //4.可执行
        test1(stringList, objectList);
    }
}
