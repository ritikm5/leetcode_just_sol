class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        // Impossible cases
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        int subsetSum = (sum + target) / 2;

        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int s = subsetSum; s >= num; s--) {
                dp[s] += dp[s - num];
            }
        }

        return dp[subsetSum];
    }
}
