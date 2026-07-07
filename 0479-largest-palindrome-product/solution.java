class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;

        long upper = (long) Math.pow(10, n) - 1;
        long lower = (long) Math.pow(10, n - 1);

        for (long left = upper; left >= lower; left--) {

            long pal = left;
            long x = left;
            while (x > 0) {
                pal = pal * 10 + x % 10;
                x /= 10;
            }

            for (long i = upper; i * i >= pal; i--) {
                if (pal % i == 0) {
                    long other = pal / i;
                    if (other >= lower && other <= upper) {
                        return (int) (pal % 1337);
                    }
                }
            }
        }

        return -1;
    }
}
