package com.java.xknowledge.se.collection.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * HashSet实践2：运行时改变存入set对象的值，导致可能相同元素添加到Set中，可能导致对象与集合其它对象相等，导致Hash无法准确访问；
 * 运行：
 * [R[count:-2], R[count:-3], R[count:5], R[count:9]]//equlas和hashcode都不同，故存储Set中
 * [R[count:-3], R[count:-3], R[count:5], R[count:9]]//第一个元素修改为-3，导致Set存在2个-3"相同的"的对象
 * [R[count:-3], R[count:5], R[count:9]]//移除-3，会移除第2个元素。第1个-3元素实际存储在-2的位置
 * hs是否包含count为-3的R对象？false//故不包含-3了，然后又count=-3的对象
 * hs是否包含count为5的R对象？true//但是包含5
 * Process finished with exit code 0
 * 参考：
 * 《疯狂Java讲义》
 */
public class HashSetTest2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));

        //打印HashSet集合，集合元素没有重复
        System.out.println(hs);

        //取出第一个元素
        Iterator it = hs.iterator();
        R first = (R) it.next();

        //为第一个元素的count实例变量赋值
        first.count = -3;     //①
        //再次输出HashSet集合，集合元素有重复元素
        System.out.println(hs);

        //删除count为-3的R对象
        hs.remove(new R(-3));   //
        //可以看到被删除了一个R元素
        System.out.println(hs);

        //输出false
        System.out.println("hs是否包含count为-3的R对象？" + hs.contains(new R(-3)));

        //输出false
        System.out.println("hs是否包含count为5的R对象？" + hs.contains(new R(5)));
    }
}


class R {
    int count;

    public R(int count) {
        this.count = count;
    }

    public String toString() {
        return "R[count:" + count + "]";
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj != null && obj.getClass() == R.class) {
            R r = (R) obj;
            if (r.count == this.count) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.count;
    }
}