package com.java.xknowledge.design.action.command;

import com.java.xknowledge.design.action.command.command.Command;

/**
 * ListView
 */
class ListView {
    //面向Command接口编程，
    private Command command;

    //动态设置Command子类，发送不同的请求
    public void setCommand(Command command) {
        this.command = command;
    }

    public void getList() {
        System.out.println("首页请求");
        String result = command.execute();
        System.out.println("当前列表:" + result);
    }
}
