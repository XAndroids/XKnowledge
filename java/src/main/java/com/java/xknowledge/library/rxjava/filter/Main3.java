package com.java.xknowledge.library.rxjava.filter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class Main3 {
    public static void main(String[] args) {
        // 使用1：根据顺序跳过数据项
        Observable.just(1, 2, 3, 4, 5)
                .skip(1)// 跳过正序的前1项
                .skipLast(2)// 跳过正序的后2项
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("just 获取到的整型事件元素是： " + integer);
                    }
                });

        // 使用2：根据时间跳过数据项
        // 发送事件特点：发送数据0-5，每隔1s发送一次，每次递增1；第1次发送延迟0s
        Observable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS)
                .skip(1, TimeUnit.SECONDS)// 跳过第1s发送的数据
                .skipLast(1, TimeUnit.SECONDS)// 跳过最后1s发送的数据
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long longer) throws Exception {
                        System.out.println("intervalRange 获取到的整型事件元素是： " + longer);
                    }
                });

        //执行输入，避免退出main线程
        try {
            char ch = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
