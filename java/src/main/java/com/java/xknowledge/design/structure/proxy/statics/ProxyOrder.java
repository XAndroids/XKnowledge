package com.java.xknowledge.design.structure.proxy.statics;

import com.java.xknowledge.design.structure.proxy.OrderService;
import com.java.xknowledge.design.structure.proxy.OutOrderServiceImpl;

class ProxyOrder implements OrderService {
    private OrderService orderService = new OutOrderServiceImpl();

    @Override
    public int savaOrder() {
        System.out.println("开始海外下订单");
        return orderService.savaOrder();
    }
}
