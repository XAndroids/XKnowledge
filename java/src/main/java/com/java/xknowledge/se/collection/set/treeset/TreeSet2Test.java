package com.java.xknowledge.se.collection.set.treeset;

import java.util.TreeSet;

/**
 * TreeSet实践：equals和compareTo相等和比较大小
 * 运行：
 * true//equals一直返回false，compareTo一直返回1，同一个对象z1会被认为不同，故能重复添加
 * [com.java.xknowledge.se.collection.set.treeset.Z@2503dbd3,
 * com.java.xknowledge.se.collection.set.treeset.Z@2503dbd3]
 * 9//但其实添加的是同一个对象，故修改第1个值，把第二个2值也修改了
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */

public class TreeSet2Test {
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        Z z1 = new Z(6);
        set.add(z1);

        //输出true，表明添加成功
        System.out.println(set.add(z1));    //①
        //下面输出set集合，将看到有两个元素
        System.out.println(set);

        //修改set集合的第一个元素的age变量
        ((Z) (set.first())).age = 9;
        //输出set集合的最后一个元素的age变量，将看到也变成了9
        System.out.println(((Z) (set.last())).age);
    }
}

class Z implements Comparable {
    int age;

    public Z(int age) {
        this.age = age;
    }

    // 重写equals()方法，总是返回true
    public boolean equals(Object obj) {
        return true;
    }

    //重写了compareTo(Object obj)方法，总是返回正整数
    public int compareTo(Object obj) {
        return 1;
    }
}


