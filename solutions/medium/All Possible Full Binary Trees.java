// Title: All Possible Full Binary Trees
            // Difficulty: Medium
            // Language: Java
            // Link: https://leetcode.com/problems/all-possible-full-binary-trees/

import java.util.*;

class Solution {
    private Map<Integer, List<TreeNode>> memo = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return new ArrayList<>();
        }

        return generate(n);
    }

    private List<TreeNode> generate(int n) {
        if (memo.containsKey(n)) {
