package com.java.xknowledge.design.action.command.headfirst.command;

import com.java.xknowledge.design.action.command.headfirst.Light;

/**
 * 关灯命令
 */
public class LightOffCommand implements Command {
	Light light;
	int level;
	public LightOffCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
        level = light.getLevel();
		light.off();
	}
 
	public void undo() {
		light.dim(level);
	}
}
