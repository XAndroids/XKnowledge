package com.java.xknowledge.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class AnagramSolution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sMap = getAnagramInfo(s);
        Map<Character, Integer> tMap = getAnagramInfo(t);
        for (Map.Entry<Character, Integer> entry : sMap.entrySet()) {
            if (!entry.getValue().equals(tMap.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> getAnagramInfo(String s) {
        char[] charArray = s.toCharArray();
//        List<Character> characterList = Arrays.asList(charArray);
        return null;
    }

    public static void main(String[] args) {

    }
}
