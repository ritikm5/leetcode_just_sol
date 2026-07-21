import java.util.*;

class Solution {
    private static final double EPS = 1e-6;

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int card : cards) {
            nums.add((double) card);
        }
        return backtrack(nums);
    }

    private boolean backtrack(List<Double> nums) {
        // When only one number remains, check if it is 24
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < EPS;
        }

        // Pick two numbers and combine them
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();

                // Add all numbers except the chosen pair
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                double a = nums.get(i);
                double b = nums.get(j);

                // Try all possible operations
                List<Double> results = new ArrayList<>();
                results.add(a + b);
                results.add(a - b);
                results.add(a * b);

                if (Math.abs(b) > EPS) {
                    results.add(a / b);
                }

                for (double result : results) {
                    next.add(result);

                    if (backtrack(next)) {
                        return true;
                    }

                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }
}

