import java.util.*;

class Solution {

    private final int[][] DIRS = {{1,0},{-1,0},{0,1},{0,-1}};

    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size();
        int n = forest.get(0).size();

        List<int[]> trees = new ArrayList<>();

        // Collect all trees
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{forest.get(i).get(j), i, j});
                }
            }
        }

        // Sort by height
        Collections.sort(trees, (a, b) -> a[0] - b[0]);

        int ans = 0;
        int sr = 0, sc = 0;

        for (int[] tree : trees) {
            int tr = tree[1];
            int tc = tree[2];

            int dist = bfs(forest, sr, sc, tr, tc);
            if (dist == -1) {
                return -1;
            }

            ans += dist;
            sr = tr;
            sc = tc;
        }

        return ans;
    }

    private int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {

        if (sr == tr && sc == tc) {
            return 0;
        }

        int m = forest.size();
        int n = forest.get(0).size();

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                if (curr[0] == tr && curr[1] == tc) {
                    return steps;
                }

                for (int[] d : DIRS) {
                    int nr = curr[0] + d[0];
                    int nc = curr[1] + d[1];

                    if (nr >= 0 && nr < m &&
                        nc >= 0 && nc < n &&
                        !visited[nr][nc] &&
                        forest.get(nr).get(nc) != 0) {

                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
