class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;

        long[] ugly = new long[n];
        ugly[0] = 1;

        int[] idx = new int[m];

        for (int i = 1; i < n; i++) {
            long next = Long.MAX_VALUE;

            // Find the next super ugly number
            for (int j = 0; j < m; j++) {
                next = Math.min(next, ugly[idx[j]] * primes[j]);
            }

            ugly[i] = next;

            for (int j = 0; j < m; j++) {
                if (ugly[idx[j]] * primes[j] == next) {
                    idx[j]++;
                }
            }
        }

        return (int) ugly[n - 1];
    }
}
