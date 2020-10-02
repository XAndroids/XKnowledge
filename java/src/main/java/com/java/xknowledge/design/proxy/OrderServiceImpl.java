package com.java.xknowledge.design.proxy;

/**
 * 本地订单
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public int savaOrder() {
        System.out.println("下单成功，订单号： 888888");
        return 888888;
    }
}
