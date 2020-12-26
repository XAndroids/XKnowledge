package com.java.xknowledge.leetcode.anagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolutionTests {
    @Test
    void testIsAnagram() {
        assertTrue(new Solution().isAnagram("anagram", "anagram"));
        assertFalse(new Solution().isAnagram("rat", "car"));
    }
}
