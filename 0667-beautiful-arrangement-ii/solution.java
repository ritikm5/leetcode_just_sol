class Solution {
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int idx = 0;

        // First part: increasing sequence
        for (int i = 1; i <= n - k - 1; i++) {
            ans[idx++] = i;
        }

        int left = n - k;
        int right = n;

        while (left <= right) {
            ans[idx++] = left++;
            if (left <= right) {
                ans[idx++] = right--;
            }
        }

        return ans;
    }
}
