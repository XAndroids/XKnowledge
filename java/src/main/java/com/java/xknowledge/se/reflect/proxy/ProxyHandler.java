package com.java.xknowledge.se.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Object realSubject;

    ProxyHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在具体调用目标对象之前，可以执行一些功能处理
        System.out.println("call doSomeThings Before");
        //调用具体目标对象的方法
        Object result = method.invoke(realSubject, args);
        //在转调用具体目标对象之后，可以执行一些功能处理
        System.out.println("call doSomeThings After");
        return result;
    }
}
