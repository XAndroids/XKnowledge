package com.java.xknowledge.leetcode.isvalid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SolutionTests {
    @Test
    void testIsValid() {
        assertFalse(Solution.isValid("(){}}{"));
    }
}
