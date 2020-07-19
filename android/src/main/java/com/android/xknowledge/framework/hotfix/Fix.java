package com.android.xknowledge.framework.hotfix;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Fix {
    public static void fix(ClassLoader classLoader, String optPath, String path) {
        //反射获取到DexPathList属性对象pathList
        Class cls;
        Field pathListField = null;
        for (cls = classLoader.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                pathListField = cls.getDeclaredField("pathList");
                pathListField.setAccessible(true);
                break;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        //从dexPathList反射找到makePathElement方法
        Method makePathDlements = null;
        Class<?> pathListCls = null;
        Object pathList = null;
        try {
            pathList = pathListField.get(classLoader);
            pathListCls = pathList.getClass();
            makePathDlements = pathListCls.getDeclaredMethod("makePathElements", List.class, File.class, List.class);
            makePathDlements.setAccessible(true);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //要插入的新dex的文件集合
        ArrayList<IOException> suppressedException = new ArrayList<>();
        List<File> list = new ArrayList<>();
        list.add(new File(path));
        Object[] pathElements = null;
        try {
            pathElements = (Object[]) makePathDlements.invoke(pathList, list, new File(optPath), suppressedException);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Object[] dexElements = null;
        Field dexElementsField = null;
        try {
            //获得PathList中的dexElements
            dexElementsField = pathListCls.getDeclaredField("dexElements");
            dexElementsField.setAccessible(true);
            dexElements = (Object[]) dexElementsField.get(pathList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        //合并这两个数组pathElements + dexElements
        Object[] newElements = (Object[]) Array.newInstance(dexElements.getClass().getComponentType(), pathElements.length + dexElements.length);
        //把pathElements复制到newElement中
        System.arraycopy(pathElements, 0, newElements, 0, pathElements.length);
        //把dexElements复制到newElement中
        System.arraycopy(dexElements, 0, newElements, pathElements.length, dexElements.length);

        //反射修改DexPathList中的dexElement的值
        try {
            dexElementsField.set(pathList, newElements);
        } catch (IllegalAccessException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
