package com.java.xknowledge.se.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型通配符：
 * List原始类型代表所有类型的List，有警告；
 * List<Object>并不是List<String>的父类，故无法使用List<Object>作为所有类型List的父类；
 * List<?>通配符代表所有类型List的父类，但只能get不能add()，因为获取是Object，但不知道应该添加什么；
 * 参考：《疯狂Java讲义》
 */
class WildcardTest {
    //1.定义一个方法，该方法有一个集合参数，集合参数的类型是不确定的。使用List原始类型，程序可以执行没有问题：
    // WildcardTest childTest = new WildcardTest();
    // List<String> list = new ArrayList<>();
    // childTest.test(list);
    //但是因为
    //List是一个有泛型声明的接口，使用List接口没有传入实际类型参数，会引起泛泛型警告：Raw use of parameterized
    //class 'List'。
    public void test(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //2.为了消除静态，考虑List接口传入实际参数类型，但List集合元素不确定，故使用List<Object>。表面看起来没问题，但
    //传入实际参数会发生编译错误：不兼容的类型: List<String>无法转换为List<Object> childTest.test2(list);
    //表明List<String>不能当成List<Ojbect>使用，即List<String>不是List<Object>的子类
    public void test2(List<Object> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    //3.为了表示各种泛型List的父类，需要使用通配符(?)，写作List<?>，它的元素类型可以匹配任何类型
    public void test3(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            //4.可以调用get()方法，获取List<?>指定索引处的元素，返回的是一个位置类型，但可以肯定是Object类型
            System.out.println(list.get(i));
        }

        //4.但是不可以调用add()方法，向List<?>添加元素，因为并不知道?是什么类型，故无法添加元素
        //Required type: capture of ? Provided: String
        //错误: 对于add(String), 找不到合适的方法list.add("aaa");
//        list.add("aaa");
        //4.但是null除外，它是所有引用类型的实例
        list.add(null);
    }

    public static void main(String[] args) {
        WildcardTest childTest = new WildcardTest();
        List<String> stringList = new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");

        //1.List<Object>可以被正常执行，但是会有警告信息
        childTest.test(stringList);

        //2.List<String>并不是List<Object>子类！！！
        //Required type:List<Object>，Provided:List<String>
//        childTest.test2(stringList);

        //3.List<?>通配符，表示任意类型List的父类
        childTest.test3(stringList);
    }

}
