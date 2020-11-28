package com.java.xknowledge.se.generics.nature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型的本质：
 * 1.ArrayList<String>并没有生成class文件，运行时被认为是同一个class类型；
 * 2.故静态方法，静态初始化块或者静态变量声明和初始化允许使用泛型；
 * 3.intanceof运算符后不能使用泛型；
 * 参考《疯狂Java讲义》泛型
 */
class NatureTest {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        //ArrayList<String>并没有生成class文件，运行时被认为是同一个class类型；
        System.out.println(l1.getClass() == l2.getClass());
    }

    public void main1(String[] args) {
        Collection list = new ArrayList<String>();
        //intanceof运算符后不能使用泛型
        //Illegal generic type for instanceof
//        if(list instanceof ArrayList<String>){
//
//        }
    }
}

//泛型类
class R<T> {
    //故静态方法，静态初始化块或者静态变量声明和初始化允许使用泛型；
    //'com.java.xknowledge.se.generics.nature.R.this' cannot be referenced from a static context
//    static T info;

    //'com.java.xknowledge.se.generics.nature.R.this' cannot be referenced from a static context
//    public static void bar(T msg) {
//
//    }

    T age;

    public void foo(T msg) {

    }
}
