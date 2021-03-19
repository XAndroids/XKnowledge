package com.java.xknowledge.design.create.prototype;

/**
 * 原型模式：
 * 参考：https://github.com/bethrobson/Head-First-Design-Patterns/blob/master/src/headfirst/designpatterns/prototype/Client.java
 */
public class Client {
    public static void main(String[] args) {
        // prototype for all Dragons
        Monster dragon = new Dragon("Dragon", false);
        // prototype for all Drakons
        Monster drakon = new Drakon("Drakon", 2, true);

        Monster ladon = makeMonsterOperation(dragon, "Ladon");
        Monster laconian = makeMonsterOperation(drakon, "Laconian");

        System.out.println(ladon);
        ladon.spitPoison();

        System.out.println(laconian);
        laconian.spitPoison();
    }

    public static Monster makeMonsterOperation(Monster monsterToCopy, String name) {
        Monster newMonster = null;
        try {
            newMonster = monsterToCopy.copy();
            newMonster.setName(name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newMonster;
    }
}