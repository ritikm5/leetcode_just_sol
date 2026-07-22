class Solution {

    public int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, r, c));
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int r, int c) {

        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length
                || grid[r][c] == 0) {
            return 0;
        }
        grid[r][c] = 0;
        int area = 1;

        area += dfs(grid, r + 1, c);
        area += dfs(grid, r - 1, c);
        area += dfs(grid, r, c + 1);
        area += dfs(grid, r, c - 1);

        return area;
    }
}

