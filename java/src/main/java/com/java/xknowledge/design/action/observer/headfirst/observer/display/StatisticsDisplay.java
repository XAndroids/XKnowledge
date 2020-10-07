package com.java.xknowledge.design.action.observer.headfirst.observer.display;

import com.java.xknowledge.design.action.observer.headfirst.observer.Observer;
import com.java.xknowledge.design.action.observer.headfirst.subject.WeatherData;

/**
 * 统计布告板，统计最小、最大和平均温度
 */
public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        tempSum += temp;    //每次数据更新时，统计计算最大、最小和平均温度
        numReadings++;

        if (temp > maxTemp) {
            maxTemp = temp;
        }

        if (temp < minTemp) {
            minTemp = temp;
        }

        display();
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
                + "/" + maxTemp + "/" + minTemp);    //显示最大/最小/平均温度
    }
}
