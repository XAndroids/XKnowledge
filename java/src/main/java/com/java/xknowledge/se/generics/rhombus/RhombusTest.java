package com.java.xknowledge.se.generics.rhombus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菱形语法实践：
 * 1.菱形语法：
 * 在Java 7以前，使用带泛型的接口、类定义变量，调用构造器对象时，构造器后面也必须带泛型；
 * 从Java 7开始，Java允许在构造器后不需要完成的泛型信息，只需要<>，Java 7可以推断<>里面应该是什么类型；
 * 菱形语法对原有的泛型并没有改变，只是简化了语法；
 * 2.菱形语法和构造器：
 * Java也允许构造器签名中声明类型参数，即泛型构造器；
 * 调用泛型构造器时，可以让根据数据参数的类型来"推断"类型参数的类型，也可以显示的指定实际的类型；
 * 泛型类包含泛型构造器时，如果显示指定了泛型构造器类型，则泛型类构造对象不能使用菱形语法；
 * 参考《疯狂的Java讲义》泛型
 */
class RhombusTest {
    public static void main0(String[] args) {
        //1.1、Java 7依赖，必须在构造对象的时候，带泛型
        List<String> fistList = new ArrayList<String>();

        //1.2、Java 7之后，自动推断HashMap中应该是String，和List<String>
        Map<String, List<String>> firstMap = new HashMap<>();
        List<String> secondList = new ArrayList<>();
        secondList.add("aaa");
        secondList.add("bbb");
        firstMap.put("222", secondList);
        for (String key : firstMap.keySet()) {
            System.out.println(key);
        }
    }

    static class Foo<E> {
        //2.1、泛型构造器，构造器方法定义泛型
        public <T> Foo(T t) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        //2.1、自动推断出T为String类型
        new Foo("aaa");

        //2.1、显示指定的是Integer类型，传入的"124"是String类型冲突
        //Actual type argument and inferred type contradict each other
//        new <Integer>Foo("124");
        new <Integer>Foo(134);


        //2.2、泛型类Foo显示执行了类类型String，构造器类型Integer，则此时不能使用菱形语法，报错如下
        //Raw use of parameterized class 'Foo<>'
        //错误: 无法推断Foo<E>的类型参数
        //        Foo<String> secondFoo = new <Integer> Foo<>(123);
        //                                                 ^
        //  原因: 不能将 '<>' 与构造器的显式类型参数一起使用
        //  其中, E是类型变量:
        //    E扩展已在类 Foo中声明的Object
//        Foo<String> secondFoo = new <Integer> Foo<>(123);
        //反之，如果不明确指定构造器泛型类型，则可以使用菱形语法
        Foo<String> firstFoo = new Foo<>("234");

    }
}
