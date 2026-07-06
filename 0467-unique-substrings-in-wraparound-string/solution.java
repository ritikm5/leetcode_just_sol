class Solution {
    public int findSubstringInWraproundString(String s) {
        int[] dp = new int[26];
        int len = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i > 0 &&
                (s.charAt(i) - s.charAt(i - 1) + 26) % 26 == 1) {
                len++;
            } else {
                len = 1;
            }

            int idx = s.charAt(i) - 'a';
            dp[idx] = Math.max(dp[idx], len);
        }

        int ans = 0;
        for (int x : dp) {
            ans += x;
        }

        return ans;
    }
}
