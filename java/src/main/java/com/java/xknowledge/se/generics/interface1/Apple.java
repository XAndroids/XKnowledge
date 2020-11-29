package com.java.xknowledge.se.generics.interface1;

import java.util.Random;

/**
 * 未传入实参的类Apple：
 * 与泛型类的定义相同，在声明类的时候，需将泛型的声明一起加到类中
 * 参考：
 * https://blog.csdn.net/waldmer/article/details/12773021
 * https://blog.csdn.net/s10461/article/details/53941091
 */
class Apple<T> implements Fruit<T> {
    private T[] info;

    public void setInfo(T[] info) {
        this.info = info;
    }

    @Override
    public T getInfo() {
        Random random = new Random();
        return info[random.nextInt(2)];
    }

    public static void main(String[] args) {
        //不传入实参的泛型接口实现类
        Apple<String> apple = new Apple<>();
        apple.setInfo(new String[]{"上等1元", "中等2元"});
        System.out.println(apple.getInfo());
        System.out.println(apple.getInfo());

        Apple<Integer> apple1 = new Apple<>();
        apple1.setInfo(new Integer[]{1, 2});
        System.out.println(apple1.getInfo());
        System.out.println(apple1.getInfo());
    }
}
