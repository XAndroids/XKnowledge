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
        //自定义扩展属性CustomPlugin2
        //参考：https://www.cnblogs.com/davenkin/p/3422009.html
        project.extensions.create("CustomPlugin2", CustomPlugin2Extensioin)

        //错误：Could not find method leftShift() for arguments after updating studio 3.4
        //<< 替换 doLast << was deprecated in Gradle 4.x and removed in Gradle 5.0
        //参考：https://stackoverflow.com/questions/55793095/could-not-find-method-leftshift-for-arguments-after-updating-studio-3-4
        project.task('CustomPlugin2').doLast {
            println "CustomPlugin2"
        }

        project.task('showTime').doLast {
            //使用自定义的属性CustomPlugin2进行相关Task的逻辑
            println "Current time is " + new Date().format(project.CustomPlugin2.timeFormat)
        }

        project.task('showDate').doLast {
            println "Current date is " + new Date().format(project.CustomPlugin2.dateFormat)
        }
    }
}

//自定义属性CustomPlugin2
class CustomPlugin2Extensioin {
    String timeFormat = "MM/dd/yyyyHH:mm:ss.SSS"
    String dateFormat = "yyyy-MM-dd"
}

