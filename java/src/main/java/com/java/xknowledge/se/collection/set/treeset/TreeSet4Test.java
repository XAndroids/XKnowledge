package com.java.xknowledge.se.collection.set.treeset;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * TreeSet定制排序：默认根据升序排序，如果使用定制排序如降序，则需要自定义Comparator接口实现
 * 运行：
 * [M[age:9], M[age:5], M[age:-3]]//降序排序
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
public class TreeSet4Test {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet(new Comparator() {
            //根据M对象的age属性来决定大小
            public int compare(Object o1, Object o2) {
                M m1 = (M) o1;//进行了强制类型转换，不能添加不同类型的对象，否则抛出类型转换异常
                M m2 = (M) o2;
                return m1.age > m2.age ? -1
                        : m1.age < m2.age ? 1 : 0;
            }
        });
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(9));
        System.out.println(ts);
    }
}


class M {
    int age;

    public M(int age) {
        this.age = age;
    }

    public String toString() {
        return "M[age:" + age + "]";
    }
}
