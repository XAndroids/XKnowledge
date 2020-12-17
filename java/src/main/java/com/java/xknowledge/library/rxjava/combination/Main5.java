package com.java.xknowledge.library.rxjava.combination;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

//combineLatest,参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main5 {
    public static void main(String[] args) {
        //FIXME 为什么第一个观察者只有最后一个数据，会和第二个观察者的所有数据结合
        Observable.combineLatest(Observable.just(1L, 2L, 3L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS),// 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        System.out.println("合并的数据是 aLong = " + aLong + ", aLong2 = " + aLong2);
                        return aLong + aLong2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("合并的结果 = " + aLong);
            }
        });
    }
}
