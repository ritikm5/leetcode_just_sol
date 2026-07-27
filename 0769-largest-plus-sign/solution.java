class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];

        // Initially every cell can have maximum arm length n
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }

        // Mark mines
        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }

        // Left -> Right and Right -> Left
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }

            count = 0;
            for (int j = n - 1; j >= 0; j--) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }

        int ans = 0;
        for (int j = 0; j < n; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);
            }

            count = 0;
            for (int i = n - 1; i >= 0; i--) {
                count = (dp[i][j] == 0) ? 0 : count + 1;
                dp[i][j] = Math.min(dp[i][j], count);

                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }
}
