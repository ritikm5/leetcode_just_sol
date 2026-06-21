class Solution {
    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {
        return helper(a % MOD, b, b.length - 1);
    }

    private int helper(int a, int[] b, int idx) {
        if (idx < 0) {
            return 1;
        }

        int lastDigit = b[idx];

        int part1 = modPow(helper(a, b, idx - 1), 10);
        int part2 = modPow(a, lastDigit);

        return (part1 * part2) % MOD;
    }

    private int modPow(int base, int exp) {
        int result = 1;
        base %= MOD;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}
