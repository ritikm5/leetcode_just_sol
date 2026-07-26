import java.util.*;

class Solution {

    private Set<String> visited = new HashSet<>();
    private StringBuilder ans = new StringBuilder();
    private int k;

    public String crackSafe(int n, int k) {

        this.k = k;

        String start = "0".repeat(n - 1);

        dfs(start);

        ans.append(start);

        return ans.toString();
    }

    private void dfs(String node) {

        for (int i = 0; i < k; i++) {

            String edge = node + i;

            if (visited.contains(edge))
                continue;

            visited.add(edge);

            dfs(edge.substring(1));

            ans.append(i);
        }
    }
}
