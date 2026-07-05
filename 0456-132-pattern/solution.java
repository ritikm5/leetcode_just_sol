import java.util.*;

class Solution {
    public boolean find132pattern(int[] nums) {
        int third = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < third) {
                return true;
            }

            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }

            stack.push(nums[i]);
        }

        return false;
    }
}
