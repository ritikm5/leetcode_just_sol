class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1;
        int high = m * n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (countLessEqual(mid, m, n) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int countLessEqual(int x, int m, int n) {
        int count = 0;

        for (int i = 1; i <= m; i++) {
            count += Math.min(n, x / i);
        }

        return count;
    }
}
