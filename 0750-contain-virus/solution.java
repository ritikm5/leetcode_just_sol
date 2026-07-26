import java.util.*;

class WordFilter {
}

class Solution {

    private static final int[][] DIRS = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int containVirus(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int ans = 0;

        while (true) {

            List<List<int[]>> regions = new ArrayList<>();
            List<Set<Integer>> frontiers = new ArrayList<>();
            List<Integer> walls = new ArrayList<>();

            boolean[][] vis = new boolean[m][n];

            // Find every infected region
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    if (grid[i][j] != 1 || vis[i][j])
                        continue;

                    List<int[]> cells = new ArrayList<>();
                    Set<Integer> frontier = new HashSet<>();
                    int[] wall = new int[1];

                    dfs(grid, i, j, vis, cells, frontier, wall);

                    regions.add(cells);
                    frontiers.add(frontier);
                    walls.add(wall[0]);
                }
            }

            if (regions.isEmpty())
                break;

            int idx = -1;
            int maxFrontier = 0;

            for (int i = 0; i < frontiers.size(); i++) {
                if (frontiers.get(i).size() > maxFrontier) {
                    maxFrontier = frontiers.get(i).size();
                    idx = i;
                }
            }

            if (maxFrontier == 0)
                break;

            ans += walls.get(idx);

            // quarantine selected region
            for (int[] cell : regions.get(idx))
                grid[cell[0]][cell[1]] = -1;

            // spread all other regions
            for (int i = 0; i < regions.size(); i++) {

                if (i == idx)
                    continue;

                for (int code : frontiers.get(i)) {
                    int r = code / n;
                    int c = code % n;
                    grid[r][c] = 1;
                }
            }
        }

        return ans;
    }

    private void dfs(
            int[][] grid,
            int r,
            int c,
            boolean[][] vis,
            List<int[]> cells,
            Set<Integer> frontier,
            int[] wall) {

        int m = grid.length;
        int n = grid[0].length;

        vis[r][c] = true;
        cells.add(new int[]{r, c});

        for (int[] d : DIRS) {

            int nr = r + d[0];
            int nc = c + d[1];

            if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                continue;

            if (grid[nr][nc] == 1 && !vis[nr][nc]) {
                dfs(grid, nr, nc, vis, cells, frontier, wall);
            } else if (grid[nr][nc] == 0) {
                wall[0]++;
                frontier.add(nr * n + nc);
            }
        }
    }
}
