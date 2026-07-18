class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Length of substring
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                // Print s[i] separately
                dp[i][j] = dp[i + 1][j] + 1;

                // Merge prints when characters match
                for (int k = i + 1; k <= j; k++) {
                    if (s.charAt(i) == s.charAt(k)) {

                        int cost = dp[i][k - 1];

                        if (k + 1 <= j)
                            cost += dp[k + 1][j];

                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
