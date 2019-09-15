package com.java.xknowledge.reflect.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //动态代理，在不修改源码的情况下，增强一些方法，在方法执行前后执行任何事情
        //FIXME 跟装饰着模式的区别是，或者封装方法前后执行相关不就可以了？？
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new ProxyHandler(real));
        proxySubject.doSomeThings();
    }
}
