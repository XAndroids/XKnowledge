package com.java.xknowledge.rxjava.function;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//onExceptionResumeNext，参考：https://www.jianshu.com/p/b0c3669affdb
public class Main6 {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("发生错误了"));
                emitter.onNext(3);
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                //onExceptionResumeNext，遇到错误时，发送1个新的Observable
                observer.onNext(11);
                observer.onNext(22);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println("接收到了事件 = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                System.out.println("对Complete事件作出响应");
            }
        });
    }
}
