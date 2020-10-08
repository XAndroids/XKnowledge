package com.java.xknowledge.design.action.iterator.menu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Lou煎饼屋的菜单实现
 */
public class PancakeHouseMenu implements Menu {
    ArrayList menuItems;    //Lou使用一个ArrayList存储它的菜单项

    public PancakeHouseMenu() {
        menuItems = new ArrayList();    //构造函数中，将每个菜单项都加入到菜单中

        addItem("K&B's Pancake Breakfast",
                "Pancakes with scrambled eggs, and toast",
                true,
                2.99);

        addItem("Regular Pancake Breakfast",
                "Pancakes with fried eggs, sausage",
                false,
                2.99);

        addItem("Blueberry Pancakes",
                "Pancakes made with fresh blueberries, and blueberry syrup",
                true,
                3.49);

        addItem("Waffles",
                "Waffles, with your choice of blueberries or strawberries",
                true,
                3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);    //加入菜单，创建一个新的菜单项对象
        menuItems.add(menuItem);    //加入菜单结合中；
    }

//    public ArrayList getMenuItems() {
//        return menuItems;
//    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();    //复用ArrayList的默认Iterator
    }
}
