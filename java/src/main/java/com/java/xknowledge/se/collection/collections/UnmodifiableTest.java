package com.java.xknowledge.se.collection.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Collections实践：创建不可变集合如List/Set/Map，避免Set和Map运行时修改后，无法正常访问和移除的问题
 * 运行：
 * Exception in thread "main" java.lang.UnsupportedOperationException
 * 	at java.util.AbstractList.add(AbstractList.java:148)
 * 	at java.util.AbstractList.add(AbstractList.java:108)
 * 	at com.java.xknowledge.se.collection.collections.UnmodifiableTest.main(UnmodifiableTest.java:23)
 *
 * Process finished with exit code 1
 * 参考：
 * 《疯狂Java讲义》
 */
class UnmodifiableTest {
    public static void main(String[] args) {
        //创建一个空的、不可改变的List对象
        List unmodifiableList = Collections.emptyList();
        //创建一个只有一个元素，且不可改变的Set对象
        Set unmodifiableSet = Collections.singleton("疯狂Java讲义");
        //创建一个普通Map对象
        Map scores = new HashMap();
        scores.put("语文", 80);
        scores.put("Java", 82);
        //返回普通Map对象对应的不可变版本
        Map unmodifiableMap = Collections.unmodifiableMap(scores);

        //下面任意一行代码都将引发UnsupportedOperationException异常
        unmodifiableList.add("测试元素");   //①
        unmodifiableSet.add("测试元素");   //②
        unmodifiableMap.put("语文", 90);   //③
    }
}
