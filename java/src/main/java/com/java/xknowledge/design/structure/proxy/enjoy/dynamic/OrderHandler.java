package com.java.xknowledge.design.structure.proxy.enjoy.dynamic;

import com.java.xknowledge.design.structure.proxy.enjoy.service.OrderService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类，实现JDK InvocationHandler接口
 */
class OrderHandler implements InvocationHandler {
    OrderService orderService;    //持有被代理类实例

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始下订单");    //代理对象调方法后，同意回调invoke方法
        return method.invoke(orderService, args);
    }
}
