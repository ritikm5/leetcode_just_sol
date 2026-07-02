class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Pacific: top row
        for (int c = 0; c < n; c++) {
            dfs(0, c, pacific, heights);
        }

        // Pacific: left column
        for (int r = 0; r < m; r++) {
            dfs(r, 0, pacific, heights);
        }

        // Atlantic: bottom row
        for (int c = 0; c < n; c++) {
            dfs(m - 1, c, atlantic, heights);
        }

        // Atlantic: right column
        for (int r = 0; r < m; r++) {
            dfs(r, n - 1, atlantic, heights);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    ans.add(Arrays.asList(r, c));
                }
            }
        }

        return ans;
    }

    private void dfs(int r, int c, boolean[][] visited, int[][] heights) {

        if (visited[r][c]) return;

        visited[r][c] = true;

        for (int[] d : dirs) {

            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nc < 0 || nr >= heights.length || nc >= heights[0].length)
                continue;

            if (visited[nr][nc])
                continue;

            if (heights[nr][nc] >= heights[r][c]) {
                dfs(nr, nc, visited, heights);
            }
        }
    }
}
