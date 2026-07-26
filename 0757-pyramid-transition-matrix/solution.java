import java.util.*;

class Solution {

    private Map<String, List<Character>> map = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {

        for (String s : allowed) {
            String key = s.substring(0, 2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s.charAt(2));
        }

        return dfs(bottom);
    }

    private boolean dfs(String row) {

        if (row.length() == 1)
            return true;

        return buildNextRow(row, 0, new StringBuilder());
    }

    private boolean buildNextRow(String row, int idx, StringBuilder next) {

        if (idx == row.length() - 1)
            return dfs(next.toString());

        String key = row.substring(idx, idx + 2);

        if (!map.containsKey(key))
            return false;

        for (char ch : map.get(key)) {

            next.append(ch);

            if (buildNextRow(row, idx + 1, next))
                return true;

            next.deleteCharAt(next.length() - 1);
        }

        return false;
    }
}
