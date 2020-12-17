package com.java.xknowledge.library.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

//firstElement（） / lastElement（）
//elementAt（）
//elementAtOrError（）
//参考：https://www.jianshu.com/p/c3a930a03855
public class Main8 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5).firstElement()// 获取第1个元素
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("firstElement 获取到的整型事件元素是： " + integer);
                    }
                });

        Observable.just(1, 2, 3, 4, 5).lastElement()// 获取最后1个元素
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("lastElement 获取到的整型事件元素是： " + integer);
                    }
                });

        // 使用1：获取位置索引 = 2的 元素
        // 位置索引从0开始
        Observable.just(1, 2, 3, 4, 5).elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("elementAt 获取到的整型事件元素是： " + integer);
                    }
                });

        // 使用2：获取的位置索引 ＞ 发送事件序列长度时，设置默认参数
        Observable.just(1, 2, 3, 4, 5).elementAt(6, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("elementAt2 获取到的整型事件元素是： " + integer);
                    }
                });

        //在elementAt（）的基础上，当出现越界情况（即获取的位置索引 ＞ 发送事件序列长度）时，即抛出异常
        Observable.just(1, 2, 3, 4, 5).elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("elementAt2 获取到的整型事件元素是： " + integer);
                    }
                });
    }
}
