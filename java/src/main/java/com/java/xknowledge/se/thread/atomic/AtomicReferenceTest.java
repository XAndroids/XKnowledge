package com.java.xknowledge.se.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Atomic更新引用类型：
 * 运行：
 * atomicReference = UserInfo{name='Mark', age=15}
 * userInfo = UserInfo{name='Mark', age=15}
 * 参考：享学2《Java 筑基-06并发基础知识补全和CAS基本原理》
 */
class AtomicReferenceTest {
    static AtomicReference<UserInfo> atomicReference;

    public static void main(String[] args) {
        //初始化atomicReference
        UserInfo userInfo = new UserInfo("Mark", 15);
        atomicReference = new AtomicReference<>(userInfo);

        //compareAndSet：比较是否为userInfo，如果是则更新为updateUser
        UserInfo updateUser = new UserInfo("Bill", 17);
        atomicReference.compareAndSet(userInfo, updateUser);

        System.out.println("atomicReference = " + atomicReference.toString());
        System.out.println("userInfo = " + userInfo.toString());
    }

    static class UserInfo {
        private volatile String name;
        private int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
