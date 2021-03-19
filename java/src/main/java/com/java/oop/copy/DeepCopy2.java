package com.java.oop.copy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 深度拷贝2：通过对象序列化实现
 * 推荐使用，如果引用类型过多，需要一个一个数据
 * 运行：
 * 1846274136
 * 284720968
 * 1639705018
 * 189568618
 * 参考：https://blog.csdn.net/ywq1016243402/article/details/103963602
 */
class DeepCopy2 implements Cloneable, Serializable {
    private String name;
    private DeepCloneObject deepCloneObject;

    public DeepCopy2(String name) {
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
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);

            return (DeepCopy2) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            DeepCopy2 deepCopy2 = new DeepCopy2("小红");
            deepCopy2.setDeepCloneObject(new DeepCloneObject("DeepCloneObject"));

            DeepCopy2 deepCopy3 = (DeepCopy2) deepCopy2.clone();

            System.out.println(deepCopy2.hashCode());
            System.out.println(deepCopy3.hashCode());
            System.out.println(deepCopy2.getDeepCloneObject().hashCode());
            System.out.println(deepCopy3.getDeepCloneObject().hashCode());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
