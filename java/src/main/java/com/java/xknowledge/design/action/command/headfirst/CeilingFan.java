package com.java.xknowledge.design.action.command.headfirst;

/**
 * 吊扇类
 */
public class CeilingFan {
	public static final int HIGH = 3;
	public static final int MEDIUM = 2;
	public static final int LOW = 1;
	public static final int OFF = 0;
	String location;
	int speed;    //吊扇的速度
 
	public CeilingFan(String location) {
		this.location = location;
		speed = OFF;
	}
  
	public void high() {   //设置吊扇的速度
		speed = HIGH;
		System.out.println(location + " ceiling fan is on high");
	} 
 
	public void medium() {
		speed = MEDIUM;
		System.out.println(location + " ceiling fan is on medium");
	}
 
	public void low() {
		speed = LOW;
		System.out.println(location + " ceiling fan is on low");
	}
  
	public void off() {
		speed = OFF;
		System.out.println(location + " ceiling fan is off");
	}
  
	public int getSpeed() {    //获取吊扇的速度
		return speed;
	}
}
