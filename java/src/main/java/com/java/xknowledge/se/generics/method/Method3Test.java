package com.java.xknowledge.se.generics.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 泛型方法和方法重载：
 * 泛型即允许通配符和上界、也允许通配符的下界
 * 参考《疯狂的Java讲义》泛型
 */
class Method3Test {
    //错误: 名称冲突: <T#1>copy(Collection<? super T#1>,Collection<T#1>)和<T#2>copy(Collection<T#2>,Collection<? extends T#2>)具有相同疑符
    //    public static <T> T copy(Collection<? super T> dest, Collection<T> src) {
    //                        ^
    //  其中, T#1,T#2是类型变量:
    //    T#1扩展已在方法 <T#1>copy(Collection<? super T#1>,Collection<T#1>)中声明的Object
    //    T#2扩展已在方法 <T#2>copy(Collection<T#2>,Collection<? extends T#2>)中声明的Object
    //1 个错误
//    public static <T> void copy(Collection<T> dest, Collection<? extends T> src) {
//        for (T t : src) {
//            dest.add(t);
//        }
//    }

    public static <T> T copy(Collection<? super T> dest, Collection<T> src) {
        T last = null;
        for (T t : src) {
            dest.add(t);
            last = t;
        }
        return last;
    }

    public static void main(String[] args) {
        //copy(dest, src)匹配第一个copy返回的是Number类型，如果匹配第二个Copy则返回Integer类型，所以编译器也
        //不知道使用安一个，编译报错！！！！！！
        //错误：Ambiguous method call. Both copy (Collection<Number>, Collection<? extends Number>)
        //in Method3Test and copy (Collection<? super Integer>, Collection<Integer>) in Method3Test 
        //match
        List<Integer> src = new ArrayList<>();
        List<Number> dest = new ArrayList<>();
        copy(dest, src);
    }
}
