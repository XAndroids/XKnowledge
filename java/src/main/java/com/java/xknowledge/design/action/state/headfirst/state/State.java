package com.java.xknowledge.design.action.state.headfirst.state;

/**
 * 抽象状态：定义了状态执行的行为方法
 */
public interface State {
	void insertQuarter();   //投币
	void ejectQuarter();    //退币
	void turnCrank();    //转动曲柄
	void dispense();    //发放糖果
}
