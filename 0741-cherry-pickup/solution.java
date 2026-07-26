class Solution {
    private int[][] grid;
    private int n;
    private Integer[][][] memo;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        memo = new Integer[2 * n][n][n];

        return Math.max(0, dfs(0, 0, 0));
    }

    private int dfs(int k, int r1, int r2) {
        int c1 = k - r1;
        int c2 = k - r2;

        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n)
            return Integer.MIN_VALUE;

        if (grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;

        if (k == 2 * n - 2)
            return grid[r1][c1];

        if (memo[k][r1][r2] != null)
            return memo[k][r1][r2];

        int best = Math.max(
                Math.max(dfs(k + 1, r1 + 1, r2 + 1),
                         dfs(k + 1, r1 + 1, r2)),
                Math.max(dfs(k + 1, r1, r2 + 1),
                         dfs(k + 1, r1, r2))
        );

        if (best == Integer.MIN_VALUE) {
            memo[k][r1][r2] = Integer.MIN_VALUE;
            return best;
        }

        int cherries = grid[r1][c1];
        if (r1 != r2 || c1 != c2)
            cherries += grid[r2][c2];

        memo[k][r1][r2] = cherries + best;
        return memo[k][r1][r2];
    }
}
