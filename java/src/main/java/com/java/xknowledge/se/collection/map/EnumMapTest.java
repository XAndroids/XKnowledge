package com.java.xknowledge.se.collection.map;

import java.util.EnumMap;

/**
 * EnumMap实践：key必须是枚举，key按照枚举定义顺序排序
 * 运行：
 * {SPRING=春暖花开, SUMMER=夏日炎炎}
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
public class EnumMapTest {
    public static void main(String[] args) {
        //创建一个EnumMap对象，该EnumMap的所有key
        //必须是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.SUMMER, "夏日炎炎");
        enumMap.put(Season.SPRING, "春暖花开");
        System.out.println(enumMap);
    }
}

enum Season {
    SPRING, SUMMER, FALL, WINTER
}
