package com.java.xknowledge.se.generics.class1;

import java.io.Serializable;

/**
 * 泛型类实践
 * 参考:https://blog.csdn.net/s10461/article/details/53941091
 */
//泛型类的基本写法
// class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
//  private 泛型标识 /*（成员变量类型）*/ var;
//  .....
//
//  }
//}
//此处T可以随便写为任意标识，常见的如T，E，K，V等形式的参数常用于标识泛型
//在实例化泛型类时，必须指定T的具体类型
class GenericsClass<T> {
    //key这个成员变量的类型T，T的类型由外部指定
    private T key;

    //泛型构造方法形参key的类型也为T，T的类型由外部指定
    public GenericsClass(T key) {
        this.key = key;
    }

    //泛型方法getKey的返回值为T，T的类型由外部指定
    public T getKey() {
        return key;
    }

    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        //传入的实参类型需与泛型的类型参数相同，即Integer
        GenericsClass<Integer> genericsInteger = new GenericsClass<>(1234);
        GenericsClass<String> genericsString = new GenericsClass<>("adcd");
        System.out.println("genericsInteger = " + genericsInteger.getKey());
        System.out.println("genericsString = " + genericsString.getKey());

        //定义泛型类型，不是必须传入泛型参数，如果传入泛型参数则会根据相应的限制，此时泛型才起作用
        //如果不传入泛型类型参数，则泛型类中的泛型方法或成员变量定义的类型可以是任意类型
        GenericsClass genericsClass1 = new GenericsClass("abc");
        GenericsClass genericsClass2 = new GenericsClass(123);
        GenericsClass genericsClass3 = new GenericsClass(true);
        System.out.println("genericsClass1 = " + genericsClass1.getKey());
        System.out.println("genericsClass2 = " + genericsClass2.getKey());
        System.out.println("genericsClass3 = " + genericsClass3.getKey());
    }
}

//设置类型形参的上限
class Apple<T extends Number> {
    T col;

    public static void main(String[] args) {
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

