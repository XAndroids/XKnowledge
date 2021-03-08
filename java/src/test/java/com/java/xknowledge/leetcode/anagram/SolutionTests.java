package com.java.xknowledge.leetcode.anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTests {
    @Test
    void testIsAnagram() {
        assertTrue(Solution.isAnagram1("anagram", "anagram"));
        assertFalse(Solution.isAnagram1("rat", "car"));
    }
}
