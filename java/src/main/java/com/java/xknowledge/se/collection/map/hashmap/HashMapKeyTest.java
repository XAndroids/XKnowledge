package com.java.xknowledge.se.collection.map.hashmap;

import java.util.HashMap;

class HashMapKeyTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        String a1 = new String("a");
        String a2 = new String("a");
        hashMap.put(a1, "a1");
        System.out.print(hashMap.get(a2));
    }
}
