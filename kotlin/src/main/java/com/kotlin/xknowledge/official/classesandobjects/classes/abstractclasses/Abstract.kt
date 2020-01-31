package com.kotlin.study.official.classesandobjects.classes.abstractclasses

//类以及其中的成员可以声明为abstract
abstract class Abstract : Base() {
    //抽象成员在本来中不用实现
    //不需要使用open标注一个抽象类或者函数，因为这不言而喻
    abstract override fun flight()
}