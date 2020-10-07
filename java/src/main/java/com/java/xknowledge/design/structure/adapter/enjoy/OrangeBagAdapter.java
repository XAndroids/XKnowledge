package com.java.xknowledge.design.structure.adapter.enjoy;

import com.java.xknowledge.design.structure.adapter.enjoy.bag.AppleBag;
import com.java.xknowledge.design.structure.adapter.enjoy.bag.OrangeBag;

/**
 * 橘子包装适配器
 * 注意：被适配的AppleBag和OrangeBag不是必须实现一个接口
 */
class OrangeBagAdapter extends OrangeBag {    //适配成橘子袋子
    private AppleBag appleBag;    //被适配的苹果袋子

    public OrangeBagAdapter(AppleBag appleBag) {
        this.appleBag = appleBag;
    }

    @Override
    public void pack() {
        System.out.println("--苹果包装塞了点泡沫，适配装橘子");
        appleBag.pack();
    }
}
