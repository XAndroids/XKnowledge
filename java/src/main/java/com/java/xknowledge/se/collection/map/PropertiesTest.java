package com.java.xknowledge.se.collection.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Properties实践：处理属性文件，将key-value形式以XML形式保存在.ini属性文件中
 * 运行：
 *
 * 参考：
 * 《疯狂的Java讲义》
 */
class PropertiesTest {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        //向Properties中增加属性
        props.setProperty("username", "yeeku");
        props.setProperty("password", "123456");
        //将Properties中的key-value对保存到a.ini文件中
        props.store(new FileOutputStream("a.ini"), "comment line");   //①

        //新建一个Properties对象
        Properties props2 = new Properties();
        //向Properties中增加属性
        props2.setProperty("gender", "male");
        //将a.ini文件中的key-value对追加到props2中
        props2.load(new FileInputStream("a.ini"));    //②
        System.out.println(props2);
    }
}
