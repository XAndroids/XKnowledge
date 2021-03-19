package com.java.oop.copy;

/**
 * 深拷贝，简单类型和引用类型都拷贝新对象
 * 运行：
 * 621009875
 * 1265094477
 * 2125039532
 * 312714112
 * 参考：https://blog.csdn.net/ywq1016243402/article/details/103963602
 */
class DeepCopy implements Cloneable {
    private String name;
    private DeepCloneObject deepCloneObject;

    public DeepCopy(String name) {
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
        DeepCopy deepCopy = (DeepCopy) super.clone();
        deepCopy.deepCloneObject = (DeepCloneObject) deepCopy.deepCloneObject.clone();
        return deepCopy;
    }

    public static void main(String[] args) {
        try {
            DeepCopy deepCopy = new DeepCopy("小红");
            deepCopy.setDeepCloneObject(new DeepCloneObject("DeepCloneObject"));

            DeepCopy deepCopy1 = (DeepCopy) deepCopy.clone();

            System.out.println(deepCopy.hashCode());
            System.out.println(deepCopy1.hashCode());
            System.out.println(deepCopy.getDeepCloneObject().hashCode());
            System.out.println(deepCopy1.getDeepCloneObject().hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
