class Solution {
    public int checkRecord(int n) {
        int MOD = 1_000_000_007;

        long[][] dp = new long[2][3];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            long[][] next = new long[2][3];

            for (int a = 0; a <= 1; a++) {
                for (int l = 0; l <= 2; l++) {
                    long cur = dp[a][l];
                    if (cur == 0) continue;

                    // Add 'P'
                    next[a][0] = (next[a][0] + cur) % MOD;

                    // Add 'A'
                    if (a == 0) {
                        next[1][0] = (next[1][0] + cur) % MOD;
                    }

                    // Add 'L'
                    if (l < 2) {
                        next[a][l + 1] = (next[a][l + 1] + cur) % MOD;
                    }
                }
            }

            dp = next;
        }

        long ans = 0;
        for (int a = 0; a <= 1; a++) {
            for (int l = 0; l <= 2; l++) {
                ans = (ans + dp[a][l]) % MOD;
            }
        }

        return (int) ans;
    }
}
