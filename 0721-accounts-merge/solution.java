import java.util.*;

class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py)
                return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        int n = accounts.size();
        DSU dsu = new DSU(n);

        // email -> account index
        Map<String, Integer> emailToAccount = new HashMap<>();

        // Union accounts sharing an email
        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                if (!emailToAccount.containsKey(email)) {
                    emailToAccount.put(email, i);
                } else {
                    dsu.union(i, emailToAccount.get(email));
                }
            }
        }

        // root -> emails
        Map<Integer, TreeSet<String>> merged = new HashMap<>();

        for (String email : emailToAccount.keySet()) {
            int accountIndex = emailToAccount.get(email);
            int root = dsu.find(accountIndex);

            merged.putIfAbsent(root, new TreeSet<>());
            merged.get(root).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for (int root : merged.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(accounts.get(root).get(0));
            list.addAll(merged.get(root));
            result.add(list);
        }

        return result;
    }
}
