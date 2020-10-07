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
 * 家庭影院外观类
 */
public class HomeTheaterFacade {
    Amplifier amp;    //通过组合保存涉及到的所有子系统
    Tuner tuner;
    DvdPlayer dvd;
    CdPlayer cd;
    Projector projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DvdPlayer dvd, CdPlayer cd, Projector projector,
                             Screen screen,
                             TheaterLights lights,
                             PopcornPopper popper) {

        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.cd = cd;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
        this.popper = popper;
    }

    public void watchMovie(String movie) {    //负责打开一切，将手动执行的每项任务依次处理，每项任务都委托给子系统中相应的组件处理
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }


    public void endMovie() {    //负责关闭一切
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }

    public void listenToCd(String cdTitle) {
        System.out.println("Get ready for an audiopile experence...");
        lights.on();
        amp.on();
        amp.setVolume(5);
        amp.setCd(cd);
        amp.setStereoSound();
        cd.on();
        cd.play(cdTitle);
    }

    public void endCd() {
        System.out.println("Shutting down CD...");
        amp.off();
        amp.setCd(cd);
        cd.eject();
        cd.off();
    }

    public void listenToRadio(double frequency) {
        System.out.println("Tuning in the airwaves...");
        tuner.on();
        tuner.setFrequency(frequency);
        amp.on();
        amp.setVolume(5);
        amp.setTuner(tuner);
    }

    public void endRadio() {
        System.out.println("Shutting down the tuner...");
        tuner.off();
        amp.off();
    }
}
