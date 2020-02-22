package com.java.xknowledge.rxjava.combination;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

//count（）,参考：https://www.jianshu.com/p/c2a7c03da16d
public class Main9 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5).count().subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("发送的事件数量 =  " + aLong);
            }
        });
    }
}
