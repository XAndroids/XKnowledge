package com.java.xknowledge.design.action.mediator;

/**
 * 同事B
 */
class ColleagueB extends IColleague {

    public ColleagueB(IMediator mediator, String name) {
        super(mediator, name);
    }

    public void operation(String message) {
        getMediator().chat(this, message);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("B ,sendMessage," + message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("B ,receiveMessage," + message);
    }
}
