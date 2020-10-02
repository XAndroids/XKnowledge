package com.java.xknowledge.design.proxy.statics;

import com.java.xknowledge.design.proxy.OrderService;

class ProxyClient {
    public static void main(String[] args) {
        OrderService orderService = new ProxyOrder();
        orderService.savaOrder();
    }
}
