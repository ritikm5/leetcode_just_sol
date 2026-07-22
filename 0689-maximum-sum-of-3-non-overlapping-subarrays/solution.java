class Solution {

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {

        int n = nums.length;

        // Prefix sums
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + nums[i];

        // left[i] = best window starting index from 0...i
        int[] left = new int[n];

        int best = 0;

        for (int i = k - 1; i < n; i++) {

            int currentStart = i - k + 1;

            if (windowSum(prefix, currentStart, k) >
                    windowSum(prefix, best, k))
                best = currentStart;

            left[i] = best;
        }

        // right[i] = best window starting index from i...end
        int[] right = new int[n];

        best = n - k;

        for (int i = n - k; i >= 0; i--) {

            if (windowSum(prefix, i, k) >=
                    windowSum(prefix, best, k))
                best = i;

            right[i] = best;
        }

        int max = 0;

        int[] ans = new int[3];

        for (int mid = k; mid <= n - 2 * k; mid++) {

            int l = left[mid - 1];
            int r = right[mid + k];

            int total =
                    windowSum(prefix, l, k)
                            + windowSum(prefix, mid, k)
                            + windowSum(prefix, r, k);

            if (total > max) {

                max = total;

                ans[0] = l;
                ans[1] = mid;
                ans[2] = r;
            }
        }

        return ans;
    }

    private int windowSum(int[] prefix, int start, int k) {
        return prefix[start + k] - prefix[start];
    }
}

