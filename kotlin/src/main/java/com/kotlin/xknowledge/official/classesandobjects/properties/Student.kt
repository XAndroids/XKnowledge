package com.kotlin.study.official.classesandobjects.properties

const val LOG: String = "Student"

class Student {
    lateinit var name:String

    var age = 0
        set(value) {
            field = if (value <= -1) {
                0
            } else {
                value
            }
        }

    companion object {
        const val LOG: String = "Student"
    }
}