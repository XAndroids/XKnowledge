package com.java.xknowledge.design.structure.bridge;

import com.java.xknowledge.design.structure.bridge.bag.BagAbstraction;
import com.java.xknowledge.design.structure.bridge.bag.BigBag;
import com.java.xknowledge.design.structure.bridge.bag.MidBag;
import com.java.xknowledge.design.structure.bridge.meterial.Paper;
import com.java.xknowledge.design.structure.bridge.meterial.Plastic;

class BridgeClient {
    public static void main(String[] args) {
        //创建大号袋子
        BagAbstraction bigBag = new BigBag();
        //用纸的材质
        bigBag.setMaterial(new Paper());
        //开始采摘
        bigBag.pick();
        //替换使用塑料材质
        bigBag.setMaterial(new Plastic());
        //开始采摘
        bigBag.pick();

        BagAbstraction midBag = new MidBag();
        midBag.setMaterial(new Paper());
        midBag.pick();
    }
}
