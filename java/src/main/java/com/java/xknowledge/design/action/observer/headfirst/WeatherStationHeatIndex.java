package com.java.xknowledge.design.action.observer.headfirst;

import com.java.xknowledge.design.action.observer.headfirst.observer.display.CurrentConditionsDisplay;
import com.java.xknowledge.design.action.observer.headfirst.observer.display.ForecastDisplay;
import com.java.xknowledge.design.action.observer.headfirst.observer.display.HeatIndexDisplay;
import com.java.xknowledge.design.action.observer.headfirst.observer.display.StatisticsDisplay;
import com.java.xknowledge.design.action.observer.headfirst.subject.WeatherData;

public class WeatherStationHeatIndex {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(82, 70, 29.2f);
		weatherData.setMeasurements(78, 90, 29.2f);
	}
}
