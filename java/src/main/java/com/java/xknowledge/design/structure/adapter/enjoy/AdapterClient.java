package com.java.xknowledge.design.structure.adapter.enjoy;

import com.java.xknowledge.design.common.fruit.Orange;
import com.java.xknowledge.design.structure.adapter.enjoy.bag.AppleBag;

/**
 * 适配器模式
 * 参考：享学《设计模式-适配器模式》
 */
class AdapterClient {
    public static void main(String[] args) {
        Orange orange = new Orange();
        //橘子包装盒没有了，把苹果包装盒适配橘子包装盒
        orange.pack(new OrangeBagAdapter(new AppleBag()));
    }
}
