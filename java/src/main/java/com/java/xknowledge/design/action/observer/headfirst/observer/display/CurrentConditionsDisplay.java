package com.java.xknowledge.design.action.observer.headfirst.observer.display;

import com.java.xknowledge.design.action.observer.headfirst.observer.Observer;
import com.java.xknowledge.design.action.observer.headfirst.subject.Subject;

/**
 * 目前状态布告板
 */
//实现了Observer接口：可以获取WeatherData的改变
//实现了DisplayElement接口：按照接口标准展示信息
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;    //自己关注的温度和湿度等数据
    private float humidity;
    private Subject weatherData;    //观察的weatherData

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);    //将目前状态布告板注册weatherData观察者
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;    //把关注的温度和湿度保存下来
        this.humidity = humidity;
        display();    //更新展示
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature
                + "F degrees and " + humidity + "% humidity");
    }
}
