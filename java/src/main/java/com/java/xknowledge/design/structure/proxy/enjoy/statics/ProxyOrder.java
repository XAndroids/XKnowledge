package com.java.xknowledge.design.structure.proxy.enjoy.statics;

import com.java.xknowledge.design.structure.proxy.enjoy.service.OrderService;
import com.java.xknowledge.design.structure.proxy.enjoy.service.OutOrderServiceImpl;

/**
 * 代理对象实现
 */
class ProxyOrder implements OrderService {
    private OrderService orderService = new OutOrderServiceImpl();    //保存真实对象索引

    @Override
    public int savaOrder() {
        System.out.println("开始海外下订单");   //也可以
        return orderService.savaOrder();    //实际调用代理对象执行
    }
}
