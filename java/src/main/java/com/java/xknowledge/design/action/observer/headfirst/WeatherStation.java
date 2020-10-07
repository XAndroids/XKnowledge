package com.java.xknowledge.design.action.observer.headfirst;

import com.java.xknowledge.design.action.observer.headfirst.observer.display.CurrentConditionsDisplay;
import com.java.xknowledge.design.action.observer.headfirst.observer.display.ForecastDisplay;
import com.java.xknowledge.design.action.observer.headfirst.observer.display.StatisticsDisplay;
import com.java.xknowledge.design.action.observer.headfirst.subject.WeatherData;

public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();    //建立一个天气数据对象

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);    //建立当前、统计和预测展示
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);    //模拟天气的测量变化
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
