package com.java.xknowledge.design.action.mediator;

/**
 * 同事A
 */
class ColleagueA extends IColleague {

    public ColleagueA(IMediator mediator, String name) {
        super(mediator, name);
    }

    public void operation(String message) {
        getMediator().chat(this, message);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("A ,sendMessage," + message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println("A ,receiveMessage," + message);
    }
}
