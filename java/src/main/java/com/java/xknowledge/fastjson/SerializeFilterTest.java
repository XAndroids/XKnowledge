package com.java.xknowledge.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;

/**
 * SerializeFilter实践类：
 * 通过SerializeFilter可以使用扩展编程方式实现定制序列化，fastjson提供了多种SerializeFilter：
 *
 * PropertyPreFilter 根据PropertyName判断是否序列化；
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化；
 * NameFilter 修改Key，如果需要修改Key，process返回值则可；
 * ValueFilter 修改Value；
 * BeforeFilter 序列化时在最前添加内容；
 * AfterFilter 序列化时在最后添加内容。
 * https://blog.csdn.net/liupeifeng3514/article/details/79167734
 */
class SerializeFilterTest {
    public static void main(String[] args) {
        JsonBean jsonBean = new JsonBean();
        NameFilter nameFiler = new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                System.out.println("object=" + object + ", name=" + name + ", value=" + value);
                if ("pid".equals(name)) {
                    return "processId";
                }
                return name;
            }
        };
        String jsonString = JSON.toJSONString(jsonBean, nameFiler);
        System.out.println("jsonString = " + jsonString);
    }
}
