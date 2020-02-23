package com.java.xknowledge.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

//distinct（） / distinctUntilChanged（），参考：https://www.jianshu.com/p/c3a930a03855
public class Main4 {
    public static void main(String[] args) {
        // 使用1：过滤事件序列中重复的事件
        Observable.just(1, 1, 3, 1, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("distinct 获取到的整型事件元素是： " + integer);
                    }
                });

        // 使用2：过滤事件序列中 连续重复的事件
        // 下面序列中，连续重复的事件 = 3、4
        Observable.just(1, 2, 3, 1, 2, 3, 3, 4, 4).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("distinctUntilChanged 获取到的整型事件元素是： " + integer);
            }
        });
    }
}
