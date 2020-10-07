package com.java.xknowledge.design.structure.facade.headfirst;

import com.java.xknowledge.design.structure.facade.headfirst.subsystem.Amplifier;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.CdPlayer;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.DvdPlayer;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.PopcornPopper;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.Projector;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.Screen;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.TheaterLights;
import com.java.xknowledge.design.structure.facade.headfirst.subsystem.Tuner;

/**
 * 外观模式
 * 参考：《Hread First 设计模式》
 */
public class HomeTheaterTestDrive {
	public static void main(String[] args) {
		//根据子系统所有的组件，创建外观实例
		Amplifier amp = new Amplifier("Top-O-Line Amplifier");
		Tuner tuner = new Tuner("Top-O-Line AM/FM Tuner", amp);
		DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
		CdPlayer cd = new CdPlayer("Top-O-Line CD Player", amp);
		Projector projector = new Projector("Top-O-Line Projector", dvd);
		TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
		Screen screen = new Screen("Theater Screen");
		PopcornPopper popper = new PopcornPopper("Popcorn Popper");
		HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, dvd, cd, projector, screen, lights, popper);
 
		homeTheater.watchMovie("Raiders of the Lost Ark");    //使用简化接口，先开启后关闭
		homeTheater.endMovie();
	}
}
