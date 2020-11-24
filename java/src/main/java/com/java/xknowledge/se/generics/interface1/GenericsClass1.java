package com.java.xknowledge.se.generics.interface1;

import java.util.Random;

/**
 * 传入泛型实参的类GenericsClass1：
 * 定义一个GenericsClass1实现接口，为T传入无数个参数，形成无数种类型的GenericsInterface接口
 * 在实现类实现泛型接口时，如已将泛型类传入实参类型，则所有使用泛型的地方都替换成传入的实参类型
 * 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
 * 参考：https://blog.csdn.net/waldmer/article/details/12773021
 * https://blog.csdn.net/s10461/article/details/53941091
 */
class GenericsClass1 implements GenericsInterface<String> {
    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }

    public static void main(String[] args) {
        //传入泛型参数泛型接口实现类
        GenericsClass1 genericsClass1 = new GenericsClass1();
        System.out.println(genericsClass1.next());
        System.out.println(genericsClass1.next());
        System.out.println(genericsClass1.next());
    }
}
