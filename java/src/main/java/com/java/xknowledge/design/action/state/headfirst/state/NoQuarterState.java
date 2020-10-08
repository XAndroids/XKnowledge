package com.java.xknowledge.design.action.state.headfirst.state;

import com.java.xknowledge.design.action.state.headfirst.GumballMachine;

/**
 * 无投币状态
 * 具体状态：具有客户端实例，实现行为方法，并根据当前的情况改变客户端的状态；
 */
public class NoQuarterState implements State {    //实现状态接口
    GumballMachine gumballMachine;    //客户端实例

    public NoQuarterState(GumballMachine gumballMachine) {    //通过构造器得到糖果机引用
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You inserted a quarter");    //如果有人投币，打印消息
        gumballMachine.setState(gumballMachine.getHasQuarterState());    //改变机器状态到hasQuarterState有币状态
    }

    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");    //如果没有给钱，就不能要求退钱
    }

    public void turnCrank() {
        System.out.println("You turned, but there's no quarter");    //如果没有给钱，就不能要求糖果
    }

    public void dispense() {
        System.out.println("You need to pay first");    //如果没有给钱，就不能要求放糖果
    }

    public String toString() {
        return "waiting for quarter";
    }
}
