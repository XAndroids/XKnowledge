package com.java.xknowledge.design.action.command;

import com.java.xknowledge.design.action.command.command.DiscountCommand;
import com.java.xknowledge.design.action.command.command.HotCommand;

/**
 * 命令模式客户端
 */
class CommandClient {
    public static void main(String[] args) {
        ListView list = new ListView();
        //动态设置具体执行Coomand实现类
        list.setCommand(new HotCommand());
        //通过command实现类，调用具体的Handler
        list.getList();

        list.setCommand(new DiscountCommand());
        list.getList();
    }
}
