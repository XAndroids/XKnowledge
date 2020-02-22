package com.java.xknowledge.rxjava.combination;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

//collect，参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main7 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6).collect(new Callable<ArrayList<Integer>>() {
            @Override
            public ArrayList<Integer> call() throws Exception {
                // 1. 创建数据结构（容器），用于收集被观察者发送的数据
                return new ArrayList<>();
            }
        }, new BiConsumer<ArrayList<Integer>, Integer>() {
            @Override
            public void accept(ArrayList<Integer> integers, Integer integer) throws Exception {
                //2. 对发送的数据进行收集
                // 参数说明：list = 容器，integer = 后者数据
                integers.add(integer);
            }
        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                System.out.println("本次发送的数据是 = " + integers);
            }
        });
    }
}
