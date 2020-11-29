package com.java.xknowledge.se.generics.wildcard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通配符的下界
 * 1.表示此类型必须是T类型本身，或者T类型的父类，使用下界<? super T>；
 * 2.上界可以add元素，因为它一定是父类，但是不能get元素，不知道是哪个子类；
 * 参考《疯狂的Java讲义》泛型
 */
class SuperTest {
    //1.1、copy(List<Number>,List<Integer>)，只能推断T为Number类型
    //故Integer last2 = copy(numberList, integerList)，会报类型异常Number无法转换为Integer
    public static <T> T copy(Collection<T> dest, Collection<? extends T> src) {
        T last = null;
        for (T t : src) {
            dest.add(t);
            last = t;
        }
        return last;
    }

    //1.2、不管src集合元素类型是什么，dest和src结合相同或者是它的父类，为了表达这种约束关系，使用通配符的下界限
    //<? super Type>，表示它必须是T本身，或者T的父类
    //copy(List<Number>,List<Integer>)，能推断T为Integer类型
    //故nteger last2 = copy2(numberList, integerList)，可以被执行
    public static <T> T copy2(List<? super T> dest, List<T> src) {
        T last = null;
        for (T t : src) {
            dest.add(t);
            last = t;
        }

        //2.1、上界add的数据，是T类型的子类，get时无法确定具体是T的哪个子类？？故报错：
        //Required type: T Provided: capture of ? super T
//        T t1 = dest.get(0);
        return last;
    }

    //2、采用1方法写法，无法保证返回值的类型是Integer，dest采用<? extends T>是无法add的
    //故如果不需要返回Integer就使用copy，需要明确Integer使用copy1，无法使用copy3，因为需要add()元素！！
    public static <T> T copy3(Collection<? extends T> dest, Collection<T> src) {
        T last = null;
        for (T t : src) {
            //上界不确定具体是哪个子类，无法添加add，但是能确定一定是父类T，可以get()
            //错误: 不兼容的类型: T无法转换为CAP#1
            //            dest.add(t);
            //                     ^
            //  其中, T是类型变量:
            //    T扩展已在方法 <T>copy3(Collection<? extends T>,Collection<T>)中声明的Object
            //  其中, CAP#1是新类型变量:
            //    CAP#1从? extends T的捕获扩展T
//            dest.add(t);
            last = t;
        }
        return last;
    }

    public static void main(String[] args) {
        List<Number> numberList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        Number last1 = copy(numberList, integerList);
        //错误: 不兼容的类型: Number无法转换为Integer， Integer last2 = copy(numberList, integerList);
//        Integer last2 = copy(numberList, integerList);

        //copy可以返回Integer和Number类型
        Integer last2 = copy2(numberList, integerList);
        Number last3 = copy2(numberList, integerList);
    }
}
