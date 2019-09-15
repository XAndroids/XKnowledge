package com.java.xknowledge.reflect.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        //动态代理，在不修改源码的情况下，增强一些方法，在方法执行前后执行任何事情
        //一般用在一些Java框架，组件化之间解耦实现代理模式
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, new ProxyHandler(real));
        proxySubject.doSomeThings();
    }
}
