package com.kotlin.study.official.classesandobjects.sealedclasses

//1.密封类表示受限制的继承结构
//3.声明一个密封类，在类名前面添加sealed修饰符
///5.密封类自身是抽象的，不能直接实力化，可以有抽象成员
sealed class Cmd

//4.虽然密封类可以有子类，但是所有子类必须在与密封类自身相同的文件中声明
open class Open(val name: String) : Cmd()

class Play(val time: String) : Cmd()

fun main(args: Array<String>) {
    val open = Open("我爱太阳")
    //2.密封类的一个子类可以有包含状态的多个实例
    val open1 = Open("你是谁")

    val play = Play("22:03")
    val other = Other("跳跳")

    isCmd(open1)
    isCmd(open)
    isCmd(play)
    isCmd(other)
}

fun isCmd(cmd: Cmd) {
    when (cmd) {
        is Other -> println("Other")
        is Open -> println("Open")
        is Play -> println("Play")
    }
}