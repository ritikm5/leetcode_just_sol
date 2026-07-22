import java.util.*;

class Solution {

    private Map<String, Integer> memo = new HashMap<>();

    public int minStickers(String[] stickers, String target) {

        int n = stickers.length;

        int[][] count = new int[n][26];

        for (int i = 0; i < n; i++) {
            for (char c : stickers[i].toCharArray()) {
                count[i][c - 'a']++;
            }
        }

        memo.put("", 0);

        int ans = dfs(count, target);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(int[][] stickers, String target) {

        if (memo.containsKey(target))
            return memo.get(target);

        int[] targetCount = new int[26];

        for (char c : target.toCharArray())
            targetCount[c - 'a']++;

        int answer = Integer.MAX_VALUE;

        for (int[] sticker : stickers) {

            // Optimization
            if (sticker[target.charAt(0) - 'a'] == 0)
                continue;

            StringBuilder remaining = new StringBuilder();

            for (int i = 0; i < 26; i++) {

                if (targetCount[i] > 0) {

                    int left = targetCount[i] - sticker[i];

                    for (int j = 0; j < Math.max(0, left); j++) {
                        remaining.append((char) ('a' + i));
                    }
                }
            }

            int next = dfs(stickers, remaining.toString());

            if (next != Integer.MAX_VALUE) {
                answer = Math.min(answer, next + 1);
            }
        }

        memo.put(target, answer);

        return answer;
    }
}

