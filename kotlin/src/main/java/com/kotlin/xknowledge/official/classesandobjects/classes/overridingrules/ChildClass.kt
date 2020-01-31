package com.kotlin.study.official.classesandobjects.classes.overridingrules

class ChildClass : BaseClass(), BaseInterface {
    //如果一个类从它的直接超类集成了相同成员的多个实现，它必须覆盖这个成员并提供自己的实现
    override fun print() {
        //为了表示从哪个超类型集成的实现，使用由尖括号中超类型名限定的super，如super<Base>
        super<BaseClass>.print()
        super<BaseInterface>.print()
    }

    override fun publish() {
        println("ChildClass publish")
    }

}
