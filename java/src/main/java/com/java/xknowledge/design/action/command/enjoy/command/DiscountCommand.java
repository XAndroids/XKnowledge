package com.java.xknowledge.design.action.command.enjoy.command;

import com.java.xknowledge.design.action.command.enjoy.handler.DiscountHandler;

/**
 * 打折命令实现类：包装打折Handler实体
 */
public class DiscountCommand implements Command {
    private DiscountHandler discountHandler = new DiscountHandler();

    @Override
    public String execute() {
        return discountHandler.getDiscount();
    }
}
