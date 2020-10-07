package com.java.xknowledge.design.action.observer.headfirst.observer;

/**
 * 观察者接口
 */
public interface Observer {
	//获取新的天气数据后，更新
	void update(float temp, float humidity, float pressure);
}
