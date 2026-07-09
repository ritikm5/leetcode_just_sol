import java.util.*;

class Solution {
    public int findRotateSteps(String ring, String key) {
        int n = ring.length();

        // Store all indices of each character in the ring
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(ring.charAt(i), k -> new ArrayList<>()).add(i);
        }

        Integer[][] memo = new Integer[n][key.length()];
        return dfs(0, 0, ring, key, map, memo);
    }

    private int dfs(int ringPos, int keyIndex,
                    String ring, String key,
                    Map<Character, List<Integer>> map,
                    Integer[][] memo) {

        if (keyIndex == key.length())
            return 0;

        if (memo[ringPos][keyIndex] != null)
            return memo[ringPos][keyIndex];

        int n = ring.length();
        int ans = Integer.MAX_VALUE;

        char target = key.charAt(keyIndex);

        for (int nextPos : map.get(target)) {
            int diff = Math.abs(nextPos - ringPos);
            int rotate = Math.min(diff, n - diff);

            ans = Math.min(ans,
                    rotate + 1 +
                    dfs(nextPos, keyIndex + 1, ring, key, map, memo));
        }

        memo[ringPos][keyIndex] = ans;
        return ans;
    }
}
