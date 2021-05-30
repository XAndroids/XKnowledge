package com.java.xknowledge.design.action.mediator;

/**
 * 消息中介者：包含不同同事引用，负责执行不同同事间的交互
 */
class MessageMediator implements IMediator {
    //同事A和B
    IColleague colleagueA;
    IColleague colleagueB;

    public void setColleagueA(IColleague colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(IColleague colleagueB) {
        this.colleagueB = colleagueB;
    }

    @Override
    public void chat(IColleague colleague, String message) {
        if (colleague == colleagueA) {
            colleagueA.sendMessage(message);
            colleagueB.receiveMessage(message);
        } else if (colleague == colleagueB) {
            colleagueB.sendMessage(message);
            colleagueA.receiveMessage(message);
        }
    }
}
