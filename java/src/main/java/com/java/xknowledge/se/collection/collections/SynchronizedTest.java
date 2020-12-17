package com.java.xknowledge.se.collection.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Collections实践：转换线程同步
 * 参考：
 * 《疯狂的Java讲义》
 */
class SynchronizedTest {
    public static void main(String[] args) {
        //下面程序创建了四个同步的集合对象
        Collection c = Collections.synchronizedCollection(new ArrayList());
        List list = Collections.synchronizedList(new ArrayList());
        Set s = Collections.synchronizedSet(new HashSet());
        Map m = Collections.synchronizedMap(new HashMap());
    }
}
