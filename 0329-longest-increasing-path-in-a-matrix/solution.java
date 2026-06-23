class Solution {
    private int[][] matrix;
    private int[][] memo;
    private int m, n;

    private final int[][] DIRS = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        m = matrix.length;
        n = matrix[0].length;

        memo = new int[m][n];

        int ans = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                ans = Math.max(ans, dfs(r, c));
            }
        }

        return ans;
    }

    private int dfs(int r, int c) {
        if (memo[r][c] != 0) {
            return memo[r][c];
        }

        int best = 1;

        for (int[] d : DIRS) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                continue;
            }

            if (matrix[nr][nc] > matrix[r][c]) {
                best = Math.max(best, 1 + dfs(nr, nc));
            }
        }

        memo[r][c] = best;
        return best;
    }
}
