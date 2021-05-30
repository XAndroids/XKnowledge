package com.java.xknowledge.design.action.mediator;

/**
 * 同事接口
 */
abstract class IColleague {
    private IMediator mediator;
    private String name;

    public IMediator getMediator() {
        return mediator;
    }

    public IColleague(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    //接收消息
    abstract void sendMessage(String message);

    //发送消息
    abstract void receiveMessage(String message);
}
