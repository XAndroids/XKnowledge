package com.android.xknowledge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE) //APT Annotation Processing Tool，在编译时候根据源码的注解生成辅助类，故SOURCE即可
public @interface BindPath {
    String value();
}
