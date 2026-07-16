class Solution {
    private static final int MOD = 1_000_000_007;

    public int kInversePairs(int n, int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int[] newDp = new int[k + 1];
            long prefix = 0;

            for (int j = 0; j <= k; j++) {
                prefix += dp[j];

                if (j >= i) {
                    prefix -= dp[j - i];
                }

                prefix = (prefix % MOD + MOD) % MOD;
                newDp[j] = (int) prefix;
            }

            dp = newDp;
        }

        return dp[k];
    }
}
