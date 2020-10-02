package com.java.xknowledge.design.proxy.statics;

import com.java.xknowledge.design.proxy.OrderService;
import com.java.xknowledge.design.proxy.OutOrderServiceImpl;

class ProxyOrder implements OrderService {
    private OrderService orderService = new OutOrderServiceImpl();

    @Override
    public int savaOrder() {
        System.out.println("开始海外下订单");
        return orderService.savaOrder();
    }
}
