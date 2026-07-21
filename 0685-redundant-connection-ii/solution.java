class Solution {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];
        int[] candidate1 = null;
        int[] candidate2 = null;
        int skip = -1;

        // Step 1: Find node with two parents
        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                candidate1 = new int[]{parent[v], v};
                candidate2 = new int[]{u, v};
                skip = i;
            }
        }

        // Initialize Union-Find
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Step 2: Union-Find
        for (int i = 0; i < n; i++) {
            if (i == skip) continue;

            int u = edges[i][0];
            int v = edges[i][1];

            int rootU = find(parent, u);

            if (rootU == v) {
                // Cycle detected
                if (candidate1 == null) {
                    return edges[i];
                }
                return candidate1;
            }

            parent[v] = rootU;
        }

        return candidate2;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
}

