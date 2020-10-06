package com.java.xknowledge.design.action.command.command;

/**
 * 执行Handler没有抽象接口，增加Command接口，包装Handler实体
 */
public interface Command {
    String execute();
}
