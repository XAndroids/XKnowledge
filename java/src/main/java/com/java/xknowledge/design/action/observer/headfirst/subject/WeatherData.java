package com.java.xknowledge.design.action.observer.headfirst.subject;

import com.java.xknowledge.design.action.observer.headfirst.observer.Observer;

import java.util.*;

/**
 * 天气数据，实现了主题(Subject）接口
 */
public class WeatherData implements Subject {
    private ArrayList observers;    //保存观察者集合
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList();    //构造函数中初始化观察者列表
    }

    @Override
    public void registerObserver(Observer o) {    //注册观察者时，添加到观察者集合中
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {    //取消观察者时，从集合中移除
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {    //更新是通知每一个观察者更新
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {    //当天气数据变化，通知所有观察者
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
