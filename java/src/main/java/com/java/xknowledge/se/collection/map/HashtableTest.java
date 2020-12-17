package com.java.xknowledge.se.collection.map;

import java.util.Hashtable;

/**
 * Hashtable实践类：
 * 运行：
 *{com.java.xknowledge.se.collection.map.A@ea60=疯狂Java讲义,
 * com.java.xknowledge.se.collection.map.A@1560b=轻量级Java EE企业应用实战,
 * com.java.xknowledge.se.collection.map.A@4d0=com.java.xknowledge.se.collection.map.B@2503dbd3}
 * true
 * true
 * com.java.xknowledge.se.collection.map.A@ea60---->疯狂Java讲义
 * com.java.xknowledge.se.collection.map.A@1560b---->轻量级Java EE企业应用实战
 *
 * Process finished with exit code 0
 * 参考：
 * 《疯狂的Java讲义》
 */
public class HashtableTest {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        ht.put(new A(60000), "疯狂Java讲义");
        ht.put(new A(87563), "轻量级Java EE企业应用实战");
        ht.put(new A(1232), new B());
        System.out.println(ht);

        //只要两个对象通过equals比较返回true，
        //Hashtable就认为它们是相等的value。
        //由于Hashtable中有一个B对象，
        //它与任何对象通过equals比较都相等，所以下面输出true。
        System.out.println(ht.containsValue("测试字符串"));  //①

        //只要两个A对象的count相等，它们通过equals比较返回true，且hashCode相等
        //Hashtable即认为它们是相同的key，所以下面输出true。
        System.out.println(ht.containsKey(new A(87563)));   //②

        //下面语句可以删除最后一个key-value对
        ht.remove(new A(1232));    //③

        //通过返回Hashtable的所有key组成的Set集合，
        //从而遍历Hashtable每个key-value对
        for (Object key : ht.keySet()) {
            System.out.print(key + "---->");
            System.out.print(ht.get(key) + "\n");
        }
    }
}


class A {
    int count;

    public A(int count) {
        this.count = count;
    }

    //根据count的值来判断两个对象是否相等。
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj != null &&
                obj.getClass() == A.class) {
            A a = (A) obj;
            return this.count == a.count;
        }
        return false;
    }

    //根据count来计算hashCode值。
    public int hashCode() {
        return this.count;
    }
}

class B {
    //重写equals()方法，B对象与任何对象通过equals()方法比较都相等
    public boolean equals(Object obj) {
        return true;
    }
}