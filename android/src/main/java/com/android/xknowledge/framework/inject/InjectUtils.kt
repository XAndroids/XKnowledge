package com.android.xknowledge.framework.inject

import android.app.Activity
import android.view.View
import java.lang.reflect.Field

object InjectUtils {
    //反射是基于CLASS
    fun injectView(activity: Activity) {
        val cls: Class<out Activity?> = activity::class.java
        //获得此类所有的成员变量
        val fields: Array<Field> = cls.declaredFields

        //遍历
        for (field in fields) {
            //判断属性是否被InjetView注解声明
            val ret: Boolean = field.isAnnotationPresent(InjectView::class.java)
            if (ret) {
                //判断这个属性上有注解了，此时就还需要获取这个注解
                val injectView: InjectView = field.getAnnotation(InjectView::class.java)
                //获取注解中设置的id值
                val id = injectView.value
                val view: View = activity.findViewById(id)
                //反射设置属性的值
                //设置访问权限，允许操作私有成员private属性，
                field.setAccessible(true)

                //设置(反射赋值)
                try {
                    //通过反射将注解中的值设置给view
                    field.set(activity, view)
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }
            }
        }
    }
}