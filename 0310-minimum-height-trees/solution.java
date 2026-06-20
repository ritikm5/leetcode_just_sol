import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }

        List<Set<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> leaves = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.offer(i);
            }
        }

        int remainingNodes = n;

        while (remainingNodes > 2) {
            int size = leaves.size();
            remainingNodes -= size;

            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();

                int neighbor = graph.get(leaf).iterator().next();

                graph.get(neighbor).remove(leaf);

                if (graph.get(neighbor).size() == 1) {
                    leaves.offer(neighbor);
                }
            }
        }

        return new ArrayList<>(leaves);
    }
}
