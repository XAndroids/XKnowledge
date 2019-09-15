package com.java.xknowledge.reflect.proxy;

public class RealSubject implements Subject {
    @Override
    public void doSomeThings() {
        System.out.println("call doSomething()");
    }
}
