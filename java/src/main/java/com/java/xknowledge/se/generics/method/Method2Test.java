package com.java.xknowledge.se.generics.method;

import java.util.List;

/**
 * 泛型方法和通配符的区别
 * 泛型方法允许类型参数被用来表示方法的一个或多个参数之间的类型依赖关系，或者方法返回值与参数之间的类型依赖关系。
 * 如果没有这样的依赖的关系，就不应该使用泛型方法，使用通配符。
 * 参考：《疯狂Java讲义》泛型
 */
class Method2Test {
    //1.使用通配符
    interface Collection<E> {
        boolean containsAll(Collection<?> c);

        boolean addAll(Collection<? extends E> c);
    }

    //1.使用泛型方法，等同于Collection的方法
    interface Collection2<E> {
        <T> boolean containsAll(Collection<T> c);

        <T extends E> boolean addAll(Collection<T> c);
    }

    //2.dest和src存在明显依赖关系，从源List复制出来的元素，必须可以"丢进"目标List中所以源List集合元素的类型只能
    //是目标集合元素的子类型或者它本身，这就是依赖关系！！！！！
    //因为该方法无需向src中添加元素，无需修改src中的元素，所以可以使用通配符，无需使用房型方法
    public static <T> void copy(List<T> dest, List<? extends T> src) {

    }

    //2.泛型方法实现，des和src有依赖关系
    public static <T, S extends T> void copy1(List<T> des, List<S> src) {

    }
}
