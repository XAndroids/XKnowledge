package com.java.xknowledge.se.collection.set.treeset;

import java.util.TreeSet;

/**
 * TreeSet实践3：TreeSet添加对象后，修改对象的属性，会改变对象的大小，但是Treeset不会改变它的顺序，甚至可能导致
 * 存储两个相同元素
 * 运行：
 * [R[count:-3], R[count:-2], R[count:5], R[count:9]]
 * [R[count:20], R[count:-2], R[count:5], R[count:-2]]//修改-3为20,9为-2。元素的位置并没有改变，但是出现两个-2
 * false//删除修改过的元素-2，会失败！
 * [R[count:20], R[count:-2], R[count:5], R[count:-2]]
 * true//删除未修改过的元素，成功！
 * [R[count:20], R[count:-2], R[count:-2]]
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
public class TreeSet3Test {
    public static void main(String[] args) {
        TreeSet ts = new TreeSet();
        ts.add(new R(5));
        ts.add(new R(-3));
        ts.add(new R(9));
        ts.add(new R(-2));
        //打印TreeSet集合，集合元素是有序排列的
        System.out.println(ts);    //①

        //取出第一个元素
        R first = (R) ts.first();
        //对第一个元素的count赋值
        first.count = 20;
        //取出最后一个元素
        R last = (R) ts.last();
        //对最后一个元素的count赋值，与第二个元素的count相同
        last.count = -2;
        //再次输出将看到TreeSet里的元素处于无序状态，且有重复元素
        System.out.println(ts);   //②

        //删除Field被改变的元素，删除失败
        System.out.println(ts.remove(new R(-2)));    //③
        System.out.println(ts);

        //删除Field没有改变的元素，删除成功
        System.out.println(ts.remove(new R(5)));   //④
        System.out.println(ts);
    }
}

class R implements Comparable {
    int count;

    public R(int count) {
        this.count = count;
    }

    public String toString() {
        return "R[count:" + count + "]";
    }

    //重写equals方法，根据count来判断是否相等
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Z.class) {
            R r = (R) obj;
            if (r.count == this.count) {
                return true;
            }
        }
        return false;
    }

    //重写compareTo方法，根据count来比较大小
    public int compareTo(Object obj) {
        R r = (R) obj;
        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}