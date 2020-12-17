package com.java.xknowledge.se.collection.set.emumset;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

/**
 * EmumSet实践2：可以复制另一个EmumSet或者另一个Collection集合所有元素创建新的EmumSet，要求所有元素必须是一个
 * 枚举类枚举值
 * 运行：
 * [SPRING, FALL]
 * Exception in thread "main" java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Enum
 * 	at java.util.RegularEnumSet.add(RegularEnumSet.java:36)
 * 	at java.util.EnumSet.copyOf(EnumSet.java:179)
 * 	at com.java.xknowledge.se.collection.set.emumset.EmumSet2Test.main(EmumSet2Test.java:20)
 * 参考：
 * 《疯狂Java讲义》
 */
class EmumSet2Test {
    public static void main(String[] args) {
        Collection c = new HashSet();
        c.clear();
        c.add(Season.FALL);
        c.add(Season.SPRING);
        //复制Collection集合中所有元素来创建EnumSet集合
        EnumSet enumSet = EnumSet.copyOf(c);   //①
        //输出[SPRING,FALL]
        System.out.println(enumSet);
        c.add("疯狂Java讲义");
        c.add("轻量级Java EE企业应用实战");
        //下面代码出现异常：因为c集合里的元素不是全部都为枚举值
        enumSet = EnumSet.copyOf(c);  //②
    }
}
