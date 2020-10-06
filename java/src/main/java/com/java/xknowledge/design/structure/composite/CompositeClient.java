package com.java.xknowledge.design.structure.composite;
import com.alibaba.fastjson.JSON;
/**
 * 组合模式
 * 参考：享学《设计模式-组合模式》
 */
class CompositeClient {
    public static void main(String[] args) {
        //跟节点
        DistrictNode root = new DistrictNode("根");
        //一线节点，子节点
        root.addChild(new DistrictNode("上海"));
        root.addChild(new DistrictNode("天津"));
        DistrictNode districtNode = new DistrictNode("北京");
        root.addChild(districtNode);
        //二级节点，子节点
        districtNode.addChild(new DistrictNode("海淀区"));
        districtNode.addChild(new DistrictNode("西城区"));
        DistrictNode districtNode2 = new DistrictNode("朝阳区");
        districtNode.addChild(districtNode2);
        //三级节点，叶子节点
        districtNode2.addChild(new LeafNode("三里屯"));
        districtNode2.addChild(new LeafNode("朝阳外街"));

        System.out.println(JSON.toJSON(root));
    }
}
