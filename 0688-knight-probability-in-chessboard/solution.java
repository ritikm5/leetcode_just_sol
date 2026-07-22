class Solution {

    private final int[][] directions = {
        {2, 1}, {2, -1},
        {-2, 1}, {-2, -1},
        {1, 2}, {1, -2},
        {-1, 2}, {-1, -2}
    };

    private Double[][][] memo;

    public double knightProbability(int n, int k, int row, int column) {
        memo = new Double[k + 1][n][n];
        return dfs(n, k, row, column);
    }

    private double dfs(int n, int movesLeft, int r, int c) {

        if (r < 0 || c < 0 || r >= n || c >= n)
            return 0.0;

        if (movesLeft == 0)
            return 1.0;

        if (memo[movesLeft][r][c] != null)
            return memo[movesLeft][r][c];

        double probability = 0.0;

        for (int[] dir : directions) {
            probability += dfs(
                    n,
                    movesLeft - 1,
                    r + dir[0],
                    c + dir[1]
            );
        }

        probability /= 8.0;

        memo[movesLeft][r][c] = probability;

        return probability;
    }
}

