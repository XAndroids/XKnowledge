package com.java.xknowledge.design.proxy.dynamic;


import com.java.xknowledge.design.proxy.OrderService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class OrderHandler implements InvocationHandler {
    OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始下订单");
        return method.invoke(orderService, args);
    }
}
