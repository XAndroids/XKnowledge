package com.java.xknowledge.design.action.iterator.menu;

import com.java.xknowledge.design.action.iterator.iterator.DinerMenuIterator;

import java.util.Iterator;

/**
 * Mel的餐厅菜单实现
 */
public class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;

    MenuItem[] menuItems;    //采用数组，控制数组的长度，取出菜单项时不用转型

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];

        addItem("Vegetarian BLT",
                "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT",
                "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day",
                "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog",
                "A hot dog, with saurkraut, relish, onions, topped with cheese",
                false, 3.05);
        addItem("Steamed Veggies and Brown Rice",
                "Steamed vegetables over brown rice", true, 3.99);
        addItem("Pasta",
                "Spaghetti with Marinara Sauce, and a slice of sourdough bread",
                true, 3.89);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {     //检查菜单的长度，是否超出了长度限制
            System.err.println("Sorry, menu is full!  Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems = numberOfItems + 1;
        }
    }

//    public MenuItem[] getMenuItems() {    //我们不需要getMenuItems方法，事实上根本不想要这个方法，因为它暴露了内部的实现（数组）
//        return menuItems;
//    }

	@Override
    public Iterator createIterator() {
        return new DinerMenuIterator(menuItems);    //返回自定义遍历DinnerMenu数组菜单的迭代器
        //return new AlternatingDinerMenuIterator(menuItems);
    }
}
