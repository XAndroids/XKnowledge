package com.java.xknowledge.se.generics.interface1;

import java.util.Random;

/**
 * 未传入实参的类GenericsClass：
 * 与泛型类的定义相同，在声明类的时候，需将泛型的声明一起加到类中
 * 参考：https://blog.csdn.net/waldmer/article/details/12773021
 * https://blog.csdn.net/s10461/article/details/53941091
 */
class GenericsClass<T> implements GenericsInterface<T> {
    private T[] fruits;

    public void setFruits(T[] fruits) {
        this.fruits = fruits;
    }

    @Override
    public T next() {
        Random random = new Random();
        return fruits[random.nextInt(3)];
    }

    public static void main(String[] args) {
        //不传入实参的泛型接口实现类
        GenericsClass<String> genericsClass = new GenericsClass<>();
        genericsClass.setFruits(new String[]{"apple", "banana", "orange"});
        System.out.println(genericsClass.next());
        System.out.println(genericsClass.next());

        GenericsClass<Integer> genericsClass1 = new GenericsClass<>();
        genericsClass1.setFruits(new Integer[]{25, 23, 3});
        System.out.println(genericsClass1.next());
        System.out.println(genericsClass1.next());
    }
}
