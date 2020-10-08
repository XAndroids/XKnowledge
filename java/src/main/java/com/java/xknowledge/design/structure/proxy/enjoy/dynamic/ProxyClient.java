package com.java.xknowledge.design.structure.proxy.enjoy.dynamic;

import com.java.xknowledge.design.structure.proxy.enjoy.service.OrderService;
import com.java.xknowledge.design.structure.proxy.enjoy.service.OrderServiceImpl;
import com.java.xknowledge.design.structure.proxy.enjoy.service.OutOrderServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 动态代理实现：
 * 参考：https://juejin.im/post/6844903744954433544#heading-7
 */
class ProxyClient {
    public static void main(String[] args) {
        //初始化被代理类，类加载器、Class和动态代理类
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        ClassLoader classLoader = orderServiceImpl.getClass().getClassLoader();
        Class[] interfaces = orderServiceImpl.getClass().getInterfaces();
        OrderHandler orderHandler = new OrderHandler(orderServiceImpl);
        //通过JDK Proxy.newProxyInstance创建代理对象
        OrderService proxyOrderService = (OrderService) Proxy.newProxyInstance(classLoader, interfaces,
                orderHandler);
        proxyOrderService.savaOrder();    //通过代理对象调用被代理方法


        OutOrderServiceImpl outOrderServiceImpl = new OutOrderServiceImpl();
        ClassLoader classLoader2 = outOrderServiceImpl.getClass().getClassLoader();
        Class[] interfaces2 = outOrderServiceImpl.getClass().getInterfaces();
        OrderHandler orderHandler2 = new OrderHandler(outOrderServiceImpl);
        OrderService proxyOrderService2 = (OrderService) Proxy.newProxyInstance(classLoader2, interfaces2, orderHandler2);
        proxyOrderService2.savaOrder();

    }
}
