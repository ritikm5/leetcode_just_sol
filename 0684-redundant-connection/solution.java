class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            int parentU = find(parent, u);
            int parentV = find(parent, v);

            if (parentU == parentV) {
                return edge; 
            }

            parent[parentU] = parentV;
        }

        return new int[0];
    }

    private int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]); 
        }
        return parent[node];
    }
}

