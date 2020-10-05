package com.java.xknowledge.design.structure.proxy.statics;

import com.java.xknowledge.design.structure.proxy.OrderService;

class ProxyClient {
    public static void main(String[] args) {
        OrderService orderService = new ProxyOrder();
        orderService.savaOrder();
    }
}
