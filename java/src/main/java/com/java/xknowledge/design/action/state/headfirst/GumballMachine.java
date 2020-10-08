package com.java.xknowledge.design.action.state.headfirst;

import com.java.xknowledge.design.action.state.headfirst.state.HasQuarterState;
import com.java.xknowledge.design.action.state.headfirst.state.NoQuarterState;
import com.java.xknowledge.design.action.state.headfirst.state.SoldOutState;
import com.java.xknowledge.design.action.state.headfirst.state.SoldState;
import com.java.xknowledge.design.action.state.headfirst.state.State;

/**
 * 客户类：当前状态，各个状态实例，实现行为方法；
 */
public class GumballMachine {
    State soldOutState;    //所有的状态都在这里
    State noQuarterState;
    State hasQuarterState;
    State soldState;

    State state = soldOutState;    //当前状态
    int count = 0;    //记录当前糖果机内装有多少个糖果

    public GumballMachine(int numberGumballs) {
        soldOutState = new SoldOutState(this);    //每一个状态构造一个实例
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);

        this.count = numberGumballs;    //初始化糖果数目，并保存在count中
        if (numberGumballs > 0) {    //如果超过0个糖果，则当前状态为noQuarterState
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();    //委托给当前状态
    }

    public void ejectQuarter() {
        state.ejectQuarter();    //委托给当前状态
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        System.out.println("A gumball comes rolling out the slot...");
        if (count != 0) {
            count = count - 1;
        }
    }

    public int getCount() {
        return count;
    }

    void refill(int count) {
        this.count = count;
        state = noQuarterState;
    }

    public State getState() {
        return state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\nMighty Gumball, Inc.");
        result.append("\nJava-enabled Standing Gumball Model #2004");
        result.append("\nInventory: " + count + " gumball");
        if (count != 1) {
            result.append("s");
        }
        result.append("\n");
        result.append("Machine is " + state + "\n");
        return result.toString();
    }
}
