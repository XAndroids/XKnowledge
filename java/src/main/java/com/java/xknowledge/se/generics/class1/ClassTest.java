package com.java.xknowledge.se.generics.class1;

import java.io.Serializable;

/**
 * 泛型类实践：
 * 1.Java允许在定义泛型类的时候，使用声明类型形参，可以在整个类中当成类型使用，如在成员变量、构造函数、成员函数；
 * 2.声明带泛型的类、接口之后，可以从该父类派生子类，但是使用这些接口、父类时不能包含类型参数；
 * 参考《疯狂的Java讲义》泛型
 */

//泛型类的基本写法
// class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
//  private 泛型标识 /*（成员变量类型）*/ var;
//  .....
//
//  }
class ClassTest {
    //1.1、定义泛型类Apple，类型参数T
    //T可以随便写为任意标识，常见的如T，E，K，V等形式的参数常用于标识泛型；
    static class Apple<T> {
        //1.2、类型参数T用在成员变量
        private T info;

        public Apple() {

        }

        //1.3、类型参数T用在构造方法
        public Apple(T info) {
            this.info = info;
        }

        //1.4、类型参数用在成员方法
        public void setInfo(T info) {
            this.info = info;
        }

        public T getInfo() {
            return this.info;
        }
    }

    public static void main(String[] args) {
        //1.5、泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入T为String后，构造器必须是String类型
        Apple<String> apple = new Apple<>("苹果");
        System.out.println(apple.getInfo());
        Apple<Double> apple1 = new Apple<>(2.0);
        System.out.println(apple1.getInfo());

        //1.6、定义泛型类型，不是必须传入泛型参数，如果传入泛型参数则会根据相应的限制，此时泛型才起作用
        //如果不传入泛型类型参数，则泛型类中的泛型方法或成员变量定义的类型可以是任意类型
        Apple apple2 = new Apple(true);
        Apple apple3 = new Apple(232342);
        System.out.println(apple2.getInfo());
        System.out.println(apple3.getInfo());
    }

    //2.1、定义A继承Apple类，Apple类不能根分型参数T
    //错误: 找不到符号
    //    class A1 extends Apple<T> {
    //                           ^
    //  符号:   类 T
    //  位置: 类 ClassTest
//    class A1 extends Apple<T> {
//
//    }

    //2.2、可以指定具体类型或者不传入类型参数
    class A2 extends Apple<String> {

        //2.3、从Apple<String>派生子类，Apple的所有T类型参数都替换成String
        @Override
        public String getInfo() {
            return "子类A2" + super.getInfo();
        }

        //'getInfo()' is already defined in 'com.java.xknowledge.se.generics.class1.ClassTest.A2'
//        public Object getInfo() {
//            return "子类";
//        }
    }

    //2.4、如果未传入具体类型，则会有泛型警告：Raw use of parameterized class 'Apple'
    class A3 extends Apple {

    }
}

//设置类型形参的上限
class Apple<T extends Number> {
    T col;

    public static void main1(String[] args) {
        Apple<Integer> ai = new Apple<>();
        Apple<Double> ad = new Apple<>();

        //4.编译异常：错误: 类型参数String不是Number子类，引起编译错误
        //Type parameter 'java.lang.String' is not within its bound; should extend 'java.lang.Number'
//        Apple<String> as = new Apple<>();
    }
}

//5.极端情况下，为类型形参设定多个上限Number和Serializable
class Orange<T extends Number & Serializable> {

}
