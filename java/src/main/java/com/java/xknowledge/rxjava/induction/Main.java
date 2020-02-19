package com.java.xknowledge.rxjava.induction;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//分步骤实现、可采用 Disposable.dispose() 切断观察者 与 被观察者 之间的连接
//参考：https://www.jianshu.com/p/a406b94f3188
public class Main {
    public static void main(String[] args) {
        //1.创建被观察者 Observable 对象
        //使用Observable.create创建观察者Observable对象
        //create()是RxJava最基本的创造事件序列的方法，此处传入一个OnSubscribe对象参数
        //当Observable被订阅时，OnSubscribe的call()方法会被自动调用，即事件序列就会依照设定依次被触发
        //即观察者会依次调用对应事件的复写方法从而响应事件，从而实现被观察者调用了观察者的回调方法，并由被观察者的事件传递
        //即观察者模式
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            //2.在复写的subscribe（）里定义需要发送的事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                //通过ObservableEmitter类对象产生事件通知观察者
                // ObservableEmitter类介绍
                // a. 定义：事件发射器
                // b. 作用：定义需要发送的事件 & 向观察者发送事件
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

                emitter.onComplete();
            }
        });

        //3.创建观察者（Observer ）对象
        Observer<Integer> observer = new Observer<Integer>() {
            //6.定义Disposable类变量
            private Disposable disposable = null;

            //4.创建对象时通过对应复写对应事件方法 从而响应对应事件
            @Override
            public void onSubscribe(Disposable d) {
                //7. 对Disposable类变量赋值
                disposable = d;
                System.out.println("observer 开始采用Subscribe连接");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("observer 对Next事件作出响应" + value);
                //8.设置在接收到第二个事件后切断观察者和被观察者的连接
                if (value == 2) {
                    disposable.dispose();
                    System.out.println("disposable 已经切断了连接：" + disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observer 对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("observer 对Complete事件作出响应");
            }
        };

        //5.通过订阅（Subscribe）连接观察者和被观察者
        observable.subscribe(observer);
    }
}
