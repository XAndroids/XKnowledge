package com.java.xknowledge.design.structure.proxy.enjoy.statics;

import com.java.xknowledge.design.structure.proxy.enjoy.service.OrderService;

/**
 * 静态代理模式
 * 参考《享学设计模式》
 */
class ProxyClient {
    public static void main(String[] args) {
        OrderService orderService = new ProxyOrder();
        orderService.savaOrder();
    }
}
