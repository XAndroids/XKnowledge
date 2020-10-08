package com.java.xknowledge.design.action.template.headfirst.hook;

import java.io.*;

/**
 * 咖啡Hook实现
 */
public class CoffeeWithHook extends CaffeineBeverageWithHook {
	@Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }

    @Override
    public boolean customerWantsCondiments() {    //覆盖了这个钩子，提供自己的功能
        String answer = getUserInput();    //让用户输入对调料的决定
        if (answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        String answer = null;
        System.out.print("Would you like milk and sugar with your coffee (y/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            answer = in.readLine();
        } catch (IOException ioe) {
            System.err.println("IO error trying to read your answer");
        }
        if (answer == null) {
            return "no";
        }
        return answer;
    }
}
