package com.java.xknowledge.se.generics.interface1;

import java.util.Random;

/**
 * 传入泛型实参的类Orange：
 * 定义一个Orange实现接口，为T传入无数个参数，形成无数种类型的Fruit接口
 * 在实现类实现泛型接口时，如已将泛型类传入实参类型，则所有使用泛型的地方都替换成传入的实参类型
 * 即：Generator<T>，public T getInfo();中的的T都要替换成传入的String类型。
 * 参考：
 * https://blog.csdn.net/waldmer/article/details/12773021
 * https://blog.csdn.net/s10461/article/details/53941091
 */
class Orange implements Fruit<String> {
    private String[] info = new String[]{"颜色橘色", "价格1元"};

    @Override
    public String getInfo() {
        Random random = new Random();
        return info[random.nextInt(2)];
    }

    public static void main(String[] args) {
        //传入泛型参数泛型接口实现类
        Orange orange = new Orange();
        System.out.println(orange.getInfo());
        System.out.println(orange.getInfo());
        System.out.println(orange.getInfo());
    }
}
