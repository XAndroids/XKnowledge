package com.java.xknowledge.design.proxy.dynamic;

import com.java.xknowledge.design.proxy.OrderService;
import com.java.xknowledge.design.proxy.OrderServiceImpl;
import com.java.xknowledge.design.proxy.OutOrderServiceImpl;

import java.lang.reflect.Proxy;

/**
 * 动态代理实现：
 * 参考：https://juejin.im/post/6844903744954433544#heading-7
 */
class ProxyClient {
    public static void main(String[] args) {
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
        ClassLoader classLoader = orderServiceImpl.getClass().getClassLoader();
        Class[] interfaces = orderServiceImpl.getClass().getInterfaces();
        OrderHandler orderHandler = new OrderHandler(orderServiceImpl);
        OrderService proxyOrderService = (OrderService) Proxy.newProxyInstance(classLoader, interfaces,
                orderHandler);
        proxyOrderService.savaOrder();


        OutOrderServiceImpl outOrderServiceImpl = new OutOrderServiceImpl();
        ClassLoader classLoader2 = outOrderServiceImpl.getClass().getClassLoader();
        Class[] interfaces2 = outOrderServiceImpl.getClass().getInterfaces();
        OrderHandler orderHandler2 = new OrderHandler(outOrderServiceImpl);
        OrderService proxyOrderService2 = (OrderService) Proxy.newProxyInstance(classLoader2, interfaces2, orderHandler2);
        proxyOrderService2.savaOrder();

    }
}
