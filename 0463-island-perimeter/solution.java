class Solution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int perimeter = 0;

        int[][] dirs = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    for (int[] d : dirs) {
                        int ni = i + d[0];
                        int nj = j + d[1];

                        if (ni >= 0 && ni < rows &&
                            nj >= 0 && nj < cols &&
                            grid[ni][nj] == 1) {
                            perimeter--;
                        }
                    }
                }
            }
        }

        return perimeter;
    }
}
