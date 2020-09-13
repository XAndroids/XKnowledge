package com.android.xknowledge1

import org.gradle.api.Plugin
import org.gradle.api.Project
//Gradle自定义插件
//参考：https://juejin.im/post/6844903837992484872#heading-12
//方法三：独立项目中编写

//错误：Unable to load class 'com.android.xknowledge1.CustomPlugin33'.
//Possible causes for this unexpected error include:
//Gradle's dependency cache may be corrupt (this sometimes occurs after a network connection timeout.)
//Re-download dependencies and sync project (requires network)
//
//The state of a Gradle build process (daemon) may be corrupt. Stopping all Gradle daemons may solve this problem.
//Stop Gradle build processes (requires restart)
//
//Your project may be using a third-party plugin which is not compatible with the other plugins in the project or the version of Gradle requested by the project.
//
//In the case of corrupt Gradle processes, you can also try closing the IDE and then killing all Java processes.
//方案：源码目录路径grovvy写错了，修改groovy正确，间接错误CustomPlugin33并没有打入到jar包中
class CustomPlugin33 implements Plugin<Project> {
    void apply(Project project) {
        project.task('CustomPlugin33').doLast {
            println "CustomPlugin33"
        }
    }
}
