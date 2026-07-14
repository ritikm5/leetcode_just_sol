import java.util.*;

class Solution {
    public int[][] outerTrees(int[][] trees) {
        int n = trees.length;
        if (n <= 1) return trees;

        Arrays.sort(trees, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        List<int[]> hull = new ArrayList<>();

        // Lower hull
        for (int[] p : trees) {
            while (hull.size() >= 2 &&
                   cross(hull.get(hull.size() - 2),
                         hull.get(hull.size() - 1),
                         p) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(p);
        }

        // Upper hull
        int lowerSize = hull.size();

        for (int i = n - 2; i >= 0; i--) {
            while (hull.size() > lowerSize &&
                   cross(hull.get(hull.size() - 2),
                         hull.get(hull.size() - 1),
                         trees[i]) < 0) {
                hull.remove(hull.size() - 1);
            }
            hull.add(trees[i]);
        }

        // Remove duplicates
        Set<String> seen = new HashSet<>();
        List<int[]> ans = new ArrayList<>();

        for (int[] p : hull) {
            String key = p[0] + "," + p[1];
            if (seen.add(key)) {
                ans.add(p);
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    private int cross(int[] o, int[] a, int[] b) {
        return (a[0] - o[0]) * (b[1] - o[1])
             - (a[1] - o[1]) * (b[0] - o[0]);
    }
}
