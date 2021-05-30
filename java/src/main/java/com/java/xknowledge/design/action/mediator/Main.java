package com.java.xknowledge.design.action.mediator;

class Main {
    public static void main(String[] args) {
        MessageMediator messageMediator = new MessageMediator();
        ColleagueA colleagueA = new ColleagueA(messageMediator, "colleagueA");
        ColleagueB colleagueB = new ColleagueB(messageMediator, "colleagueB");
        messageMediator.setColleagueA(colleagueA);
        messageMediator.setColleagueB(colleagueB);

        colleagueA.operation("A message");
        colleagueB.operation("B message");
    }
}
