package com.java.xknowledge.se.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 集合性能测试
 * 需求存储10000个学生数据，ArrayList、LinkedList和HashMap数据结构的性能对比
 * 1.从集合中间增加数据，linkedList>hashMap>arrayList
 * arrayList insert time = 48
 * linkedList insert time = 7
 * hashMap insert time = 14
 * 2.从集合中遍历获取数据，arrayList>hashMap>linkedList，hashMap最均衡
 * arrayList get time = 3
 * linkedList get time = 670
 * hashMap get time = 8
 * 结论：
 * 中间插入数据较多，选择LinkedList
 * 遍历访问数据较多，选择ArrayList
 * 插入和访问都有，选择HashMap
 **/
class PerformanceTest {
    //顺序表
    static ArrayList<Student> arrayList = new ArrayList<>();
    //链表
    static LinkedList<Student> linkedList = new LinkedList<>();
    //HashMap
    static HashMap<Integer, Student> hashMap = new HashMap<>();

    public static void main(String[] args) {
        //从集合中间增加数据
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student(i, "student" + i);
            arrayList.add(0, student);
        }
        System.out.println("arrayList insert time = " + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student(i, "student" + i);
            linkedList.addFirst(student);
        }
        System.out.println("linkedList insert time = " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student(i, "student" + i);
            hashMap.put(student.id, student);
        }
        System.out.println("hashMap insert time = " + (System.currentTimeMillis() - start2));


        //从集合中遍历获取数据
        long start4 = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        System.out.println("arrayList get time = " + (System.currentTimeMillis() - start4));

        long start5 = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        System.out.println("linkedList get time = " + (System.currentTimeMillis() - start5));

        long start6 = System.currentTimeMillis();
        for (int i = 0; i < hashMap.size(); i++) {
            hashMap.get(i);
        }
        System.out.println("hashMap get time = " + (System.currentTimeMillis() - start6));
    }

    static class Student {
        int id;
        String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
