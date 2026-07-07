import java.util.*;

class Solution {
    private Map<String, Integer> memo = new HashMap<>();
    private final int INF = 6;

    public int findMinStep(String board, String hand) {
        int[] cnt = new int[26];
        for (char c : hand.toCharArray()) {
            cnt[c - 'A']++;
        }

        int ans = dfs(board, cnt);
        return ans == INF ? -1 : ans;
    }

    private int dfs(String board, int[] cnt) {
        board = remove(board);

        if (board.length() == 0) {
            return 0;
        }

        String key = board + Arrays.toString(cnt);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = INF;

        for (int i = 0; i <= board.length(); i++) {
            for (char c : new char[]{'R', 'Y', 'B', 'G', 'W'}) {

                if (cnt[c - 'A'] == 0) continue;

                // Pruning
                if (i > 0 && board.charAt(i - 1) == c) continue;

                boolean useful = false;

                if (i < board.length() && board.charAt(i) == c) {
                    useful = true;
                }

                if (i > 0 && i < board.length()
                        && board.charAt(i - 1) == board.charAt(i)
                        && board.charAt(i) != c) {
                    useful = true;
                }

                if (!useful) continue;

                cnt[c - 'A']--;

                String next = board.substring(0, i) + c + board.substring(i);

                int temp = dfs(next, cnt);

                if (temp != INF) {
                    res = Math.min(res, temp + 1);
                }

                cnt[c - 'A']++;
            }
        }

        memo.put(key, res);
        return res;
    }

    private String remove(String board) {
        int i = 0;

        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }

            if (j - i >= 3) {
                return remove(board.substring(0, i) + board.substring(j));
            }

            i = j;
        }

        return board;
    }
}
