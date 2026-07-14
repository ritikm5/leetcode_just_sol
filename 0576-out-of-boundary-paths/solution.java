class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][][] dp;
    private int m, n;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;

        dp = new int[m][n][maxMove + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return dfs(startRow, startColumn, maxMove);
    }

    private int dfs(int row, int col, int movesLeft) {
        // Ball is out of boundary
        if (row < 0 || row >= m || col < 0 || col >= n) {
            return 1;
        }

        // No moves left
        if (movesLeft == 0) {
            return 0;
        }

        if (dp[row][col][movesLeft] != -1) {
            return dp[row][col][movesLeft];
        }

        long ans = 0;

        ans += dfs(row - 1, col, movesLeft - 1);
        ans += dfs(row + 1, col, movesLeft - 1);
        ans += dfs(row, col - 1, movesLeft - 1);
        ans += dfs(row, col + 1, movesLeft - 1);

        dp[row][col][movesLeft] = (int) (ans % MOD);
        return dp[row][col][movesLeft];
    }
}
