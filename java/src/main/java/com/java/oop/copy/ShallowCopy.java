package com.java.oop.copy;

/**
 * 浅拷贝
 * Cloneable接口什么方法都没有，作用是运行时通知虚拟机可以安全地在实现了此接口类上使用clone方法
 * 运行：
 * 621009875
 * 1265094477
 * 2125039532
 * 2125039532
 * 参考：https://blog.csdn.net/ywq1016243402/article/details/103963602
 */
class ShallowCopy implements Cloneable {
    private String name;
    private int age;
    private String color;
    private DeepCloneObject deepCloneObject;

    public ShallowCopy(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeepCloneObject getDeepCloneObject() {
        return deepCloneObject;
    }

    public void setDeepCloneObject(DeepCloneObject deepCloneObject) {
        this.deepCloneObject = deepCloneObject;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (ShallowCopy) super.clone();
    }

    public static void main(String[] args) {
        try {
            ShallowCopy shallowCopy = new ShallowCopy("tom", 1, "白色");
            shallowCopy.setDeepCloneObject(new DeepCloneObject("DeepCloneObject"));

            ShallowCopy shallowCopy2 = (ShallowCopy) shallowCopy.clone();

            System.out.println(shallowCopy.hashCode());
            System.out.println(shallowCopy2.hashCode());
            System.out.println(shallowCopy.getDeepCloneObject().hashCode());
            System.out.println(shallowCopy2.getDeepCloneObject().hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
