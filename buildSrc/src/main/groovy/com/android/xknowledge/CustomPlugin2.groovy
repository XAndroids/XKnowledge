package com.android.xknowledge

import org.gradle.api.Plugin
import org.gradle.api.Project
//Gradle自定义插件
//参考：https://juejin.im/post/6844903837992484872#heading-12
//方法二：buildSrc工程项目
//自定义插件：实现Plugin类接口，重写apply方法
//错误：groovy和resources需要右键设置为源码和资源目录
class CustomPlugin2 implements Plugin<Project> {
    void apply(Project project) {
        //错误：Could not find method leftShift() for arguments after updating studio 3.4
        //<< 替换 doLast << was deprecated in Gradle 4.x and removed in Gradle 5.0
        //参考：https://stackoverflow.com/questions/55793095/could-not-find-method-leftshift-for-arguments-after-updating-studio-3-4
        project.task('CustomPlugin2').doLast {
            println "CustomPlugin2"
        }
    }
}
