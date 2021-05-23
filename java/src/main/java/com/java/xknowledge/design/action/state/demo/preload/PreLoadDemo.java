package com.java.xknowledge.design.action.state.demo.preload;

import com.java.xknowledge.design.action.state.demo.preload.qpload.LoadCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 需求：异步执行预加载QP任务，以hybridid为唯一id标识加载任务
 * 如果任务完成还没有观察者则等待30秒，有观察者立即返回移除，超时还没有观察者移除；
 * 如果任务未完成添加观察者，则等待任务完成回调观察者，立即移除；
 * 实现：
 * 抽象QPLoad类：代表一个HybridId的加载，包含加载成功QP字符串、加载状态和QP观察者
 * 抽象LoadState类(状态设计模式)：代表QP加载状态
 * State_Init：任务初始化状态
 * start()：开始执行加载任务，变更为State_Loading状态；
 * State_Loading：任务正在加载状态
 * complete()：完成加载任务，保存缓存QP数据，更新为State_Complete，倒计时30秒，再没有监听者移除，变更为State_Destroy
 * listen()：监听加载任务，添加加载观察则，更新为State_Listening
 * State_Complete：任务加载完成状态，等待数据观察者
 * listen()：监听完成数据，立即回调，移除加载任务，变更为State_Done
 * timeout()：完成任务超时未有观察者，移除任务，变更为State_Destroy
 * State_Listening：任务监听状态，任务未加载完，已经被观察，等待任务完成立即回调观察者，立即移除
 * listen()：添加任务观察者，还是State_Listening状态；
 * complete()：立即回调所有观察者，立即变更为State_Done状态，立即移除
 * State_Done：任务完成加载，完成数据监听回调，立即移除
 * State_Destroy：任务销毁状态，加载完成没有任何观察者
 * QPLoadManager：保存QP加载任务结合，ConcurrentHash确保Map线程安全，线程池执行异步任务
 * 总结：一个对象，指向一些动作，在不同的状态流转，不同的状态执行相同的动作，不同的行为
 */
class PreLoadDemo {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);

        QPLoadManager.getInstance().loadQP("hotel", () -> {
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String result = "hotel qp result";
            System.out.println(String.format("%s ,load = %s", simpleDateFormat.format(new Date()),
                    result));
            return result;
        });

        try {
            Thread.sleep(6 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        QPLoadManager.getInstance().listenQP("hotel", new LoadCallback() {
            @Override
            public void onResult(String qpString) {
                System.out.println(String.format("%s ,hotel callback = " + qpString, simpleDateFormat
                        .format(new Date())));
            }
        });

        QPLoadManager.getInstance().listenQP("flight", new LoadCallback() {
            @Override
            public void onResult(String qpString) {
                System.out.println(String.format("%s ,flight callback = " + qpString, simpleDateFormat
                        .format(new Date())));
            }
        });
    }
}
