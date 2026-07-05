import java.util.*;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        Map<Long, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {

                long diff = (long) nums[i] - (long) nums[j];

                int prev = dp[j].getOrDefault(diff, 0);

                ans += prev;

                dp[i].put(diff,
                        dp[i].getOrDefault(diff, 0) + prev + 1);
            }
        }

        return ans;
    }
}
