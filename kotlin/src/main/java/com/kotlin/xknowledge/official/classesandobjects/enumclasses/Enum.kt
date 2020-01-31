package com.kotlin.study.official.classesandobjects.enumclasses

//1.枚举类的最基本用法是实现类型安全的枚举
//每个枚举都是一个对象，枚举常量用逗号分隔
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

//2.每一个枚举都是枚举类的实例
enum class Color(val agb: Int) {
    REA(0xFF000) {
        override fun next(): Color {
            return GREEN
        }
    },
    GREEN(0x00FF00) {
        override fun next(): Color {
            return BLUE
        }

    },
    BLUE(0x0000FF) {
        override fun next(): Color {
            return REA
        }

    };

    //3.枚举可以声明自己的匿名类
    abstract fun next(): Color
}

fun main(args: Array<String>) {
    //4.Kotlin中枚举类也有合成方法允许列出定义的枚举常量以及通过名称获取枚举常量
    println(Direction.values())
    println(Color.valueOf("RED"))
}