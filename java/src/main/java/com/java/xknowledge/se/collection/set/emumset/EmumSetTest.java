package com.java.xknowledge.se.collection.set.emumset;

import java.util.EnumSet;

/**
 * EmumSet实践：
 * 转为枚举类设计的集合类，必须指定枚举类型的枚举值，该枚举类型在创建EmumSet显示或隐式指定；
 * 元素也是有序的，以枚举值在Emun类中的顺序来决定集合的顺序；
 * 运行：
 * [SPRING, SUMMER, FALL, WINTER]//枚举类显示指定
 * []//枚举类不指定，后续添加
 * [SPRING, WINTER]//范围初始化枚举
 * [SUMMER, WINTER]
 * [SUMMER, FALL, WINTER]
 * [SPRING]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
class EmumSetTest {
    public static void main(String[] args) {
        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        System.out.println(es1);
        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet es2 = EnumSet.noneOf(Season.class);
        //输出[]
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        System.out.println(es2);
        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
        //输出[SUMMER,WINTER]
        System.out.println(es3);
        EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        System.out.println(es4);
        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        System.out.println(es5);
    }
}

enum Season {
    SPRING, SUMMER, FALL, WINTER
}
