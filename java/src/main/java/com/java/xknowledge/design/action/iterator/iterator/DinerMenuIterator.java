package com.java.xknowledge.design.action.iterator.iterator;

import com.java.xknowledge.design.action.iterator.menu.MenuItem;

import java.util.Iterator;

/**
 * DinerMenu迭代器
 */
public class DinerMenuIterator implements Iterator {    //实现java.util.Iterator接口
    MenuItem[] list;   //遍历的集合
    int position = 0;    //遍历的索引

    public DinerMenuIterator(MenuItem[] list) {    //构造函数传入菜单项作为参数
        this.list = list;
    }

    @Override
    public Object next() {
        MenuItem menuItem = list[position];    //返回数组下一项，并递增位置
        position = position + 1;
        return menuItem;
    }

    @Override
    public boolean hasNext() {
        if (position >= list.length || list[position] == null) {    //检查取出数组所有元素
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException
                    ("You can't remove an item until you've done at least one next()");
        }
        if (list[position - 1] != null) {
            for (int i = position - 1; i < (list.length - 1); i++) {
                list[i] = list[i + 1];
            }
            list[list.length - 1] = null;
        }
    }
}
