package com.java.xknowledge.se.generics.effect;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型作用实践类：
 * 1.避免类型转换；
 * 2.运行时异常提前到编译期；
 * 参考：https://zhuanlan.zhihu.com/p/28242753
 */
class EffectTest {
    public static void main0(String[] args) {
        //泛型作用1：避免强制转换
        //Raw use of parameterized class 'ArrayList'
        List list = new ArrayList();
        //Unchecked call to 'add(E)' as a member of raw type 'java.util.List'
        list.add("abc");
        String s = (String) list.get(0);
        System.out.println("s = " + s);

        //使用泛型后,保存入和取出的是String类型，故不需要类型转换
        List<String> list2 = new ArrayList<>();
        list2.add("abc");
        String s2 = list2.get(0);
        System.out.println("s2 = " + s2);
    }

    public static void main(String[] args) {
        //泛型作用2：将运行时错误提前到编译时
        //string = abctest
        //Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        //	at com.java.xknowledge.se.generics.effect.GenericsTest.main(GenericsTest.java:33)
        //因为没有使用泛型，object都可以保存在list中，但后续运行时强制类型转换会抛异常
//        List list = new ArrayList();
//        list.add("abc");
//        list.add(1);
//        for (int i = 0; i < list.size(); i++) {
//            StringBuffer string = new StringBuffer((String) list.get(i));
//            string.append("test");
//            System.out.println("string = " + string);
//        }

        List<String> list2 = new ArrayList<>();
        list2.add("abc");
        //使用泛型后，编译时进行类型检查，如果不匹配则抛出异常
        //generics/GenericsTest.java:43: 错误: 对于add(int), 找不到合适的方法
        //        list2.add(1);
        //list2.add(1);
        for (int i = 0; i < list2.size(); i++) {
            StringBuffer string2 = new StringBuffer((String) list2.get(i));
            string2.append("test");
            System.out.println("string = " + string2);
        }
    }
}
