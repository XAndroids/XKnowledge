package com.java.xknowledge.design.action.command.enjoy.command;

import com.java.xknowledge.design.action.command.enjoy.handler.HotHandler;

/**
 * 热门命令实现类：包装热门Handler实体
 */
public class HotCommand implements Command {
    private HotHandler hotHandler = new HotHandler();

    @Override
    public String execute() {
        return hotHandler.getHots();
    }
}
