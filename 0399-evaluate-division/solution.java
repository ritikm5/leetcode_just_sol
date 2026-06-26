class Solution {

    class Pair {
        String node;
        double weight;

        Pair(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {

        Map<String, List<Pair>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            graph.computeIfAbsent(a, k -> new ArrayList<>())
                    .add(new Pair(b, val));

            graph.computeIfAbsent(b, k -> new ArrayList<>())
                    .add(new Pair(a, 1.0 / val));
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                ans[i] = -1.0;
            } else if (src.equals(dest)) {
                ans[i] = 1.0;
            } else {
                ans[i] = dfs(src, dest, graph, new HashSet<>(), 1.0);
            }
        }

        return ans;
    }

    private double dfs(String curr,
                       String target,
                       Map<String, List<Pair>> graph,
                       Set<String> visited,
                       double product) {

        if (curr.equals(target))
            return product;

        visited.add(curr);

        for (Pair next : graph.get(curr)) {
            if (visited.contains(next.node))
                continue;

            double res = dfs(next.node,
                             target,
                             graph,
                             visited,
                             product * next.weight);

            if (res != -1.0)
                return res;
        }

        return -1.0;
    }
}
