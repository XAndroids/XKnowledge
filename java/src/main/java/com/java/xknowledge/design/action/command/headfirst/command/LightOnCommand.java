package com.java.xknowledge.design.action.command.headfirst.command;

import com.java.xknowledge.design.action.command.headfirst.Light;

/**
 * 开灯命令
 */
public class LightOnCommand implements Command {
	Light light;
	int level;

	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
        level = light.getLevel();
		light.on();
	}
 
	public void undo() {
		light.dim(level);
	}
}
