package com.java.xknowledge.se.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型通配符，通配符上界，类型形参上限
 * 1.List<Shape>不是List<Circle>和List<Rectangle>的父类；
 * 2.故List<?>代表List<Circle>等的父类，但是需要类型转换；
 * 3.List<? exntends Shape>通配符上界表示List<Circle>和List<Rectangle>的父类，但是无法add()，因为也不知道具体
 * 类型
 * 参考：《疯狂Java讲义》
 */

//定义抽象类Shape
abstract class Shape {
    public abstract void draw(Canvas canvas);
}

//定义子类Circle
class Circle extends Shape {
    @Override
    public void draw(Canvas canvas) {
        System.out.println("画圆");
    }
}

//定义子类Rectangle
class Rectangle extends Shape {

    @Override
    public void draw(Canvas canvas) {
        System.out.println("画矩形");
    }
}

class Canvas {
    //1.绘制所有Shape类型的集合，由于Circle是Shape的子类，但是List<Circle>并不是List<Shape>的子类
    //故：
    //List<Circle> shapeList = new ArrayList<>();
    //shapeList.add(new Circle());
    //错误: 不兼容的类型: List<Circle>无法转换为List<Shape> canvas.drawAll(shapeList);
    //canvas.drawAll(shapeList);
    public void drawAll(List<Shape> shapeList) {
        for (Shape shape : shapeList) {
            shape.draw(this);
        }
    }

    //2.使用通配符List<?>代表List<Circle>的父类，但必须要类型转换
    public void drawAll1(List<?> shapeList) {
        for (Object object : shapeList) {
            ((Shape) object).draw(this);
        }
    }

    //3.使用泛型通配符上界<? extends Shape>可以表示List<Circle>和List<Rectangle>的父类
    public void drawAll2(List<? extends Shape> shapeList) {
        for (Shape shape : shapeList) {
            shape.draw(this);
        }

        //3.但是无法添加，虽然限定Shape子类，但也不知道是Circle和Rectangle，故不能add()添加
        //equired type: capture of ? extends Shape Provided: Circle
        //错误: 对于add(Circle), 找不到合适的方法 shapeList.add(new Circle());
//        shapeList.add(new Circle());
    }
}

class ExtendsTest {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        List<Circle> shapeList = new ArrayList<>();
        shapeList.add(new Circle());
        //1.错误: 不兼容的类型: List<Circle>无法转换为List<Shape> canvas.drawAll(shapeList);
//        canvas.drawAll(shapeList);

        canvas.drawAll1(shapeList);

        canvas.drawAll2(shapeList);
    }
}
