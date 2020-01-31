package com.kotlin.study.official.classesandobjects.properties

fun main(args: Array<String>) {
    var student = Student()
    student.age = 2
    print(student.age)

    print(Student.LOG)
}