class Solution {
    private static final int MOD = 1000000007;

    private long single(char c) {
        if (c == '*') return 9;
        if (c == '0') return 0;
        return 1;
    }

    private long pair(char a, char b) {
        if (a == '*' && b == '*') {
            return 15; // 11-19 and 21-26
        }

        if (a == '*') {
            if (b >= '0' && b <= '6') {
                return 2; // 1b or 2b
            } else {
                return 1; // only 1b
            }
        }

        if (b == '*') {
            if (a == '1') return 9; // 11-19
            if (a == '2') return 6; // 21-26
            return 0;
        }

        int num = (a - '0') * 10 + (b - '0');
        return (num >= 10 && num <= 26) ? 1 : 0;
    }

    public int numDecodings(String s) {
        int n = s.length();

        long prev2 = 1;
        long prev1 = single(s.charAt(0));

        for (int i = 1; i < n; i++) {
            long curr = (prev1 * single(s.charAt(i))) % MOD;
            curr = (curr + (prev2 * pair(s.charAt(i - 1), s.charAt(i))) % MOD) % MOD;

            prev2 = prev1;
            prev1 = curr;
        }

        return (int) prev1;
    }
}
