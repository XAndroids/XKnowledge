package com.java.xknowledge.library.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

//reduce，参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main6 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                System.out.println("本次计算数据 integer = " + integer + ",integer2 = " + integer2);
                return integer * integer2;
                // 本次聚合的逻辑是：全部数据相乘起来
                // 原理：第1次取前2个数据相乘，之后每次获取到的数据 = 返回的数据x原始下1个数据每
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("最终结果是 = " + integer);
            }
        });
    }
}
