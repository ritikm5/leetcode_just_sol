class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        return mergeSort(prefix, 0, prefix.length, lower, upper);
    }

    private int mergeSort(long[] sums, int left, int right, int lower, int upper) {
        if (right - left <= 1) {
            return 0;
        }

        int mid = left + (right - left) / 2;

        int count = mergeSort(sums, left, mid, lower, upper)
                  + mergeSort(sums, mid, right, lower, upper);

        int low = mid;
        int high = mid;

        for (int i = left; i < mid; i++) {
            while (low < right && sums[low] - sums[i] < lower) {
                low++;
            }
            while (high < right && sums[high] - sums[i] <= upper) {
                high++;
            }
            count += high - low;
        }

        // Merge sorted halves
        long[] temp = new long[right - left];
        int i = left, j = mid, k = 0;

        while (i < mid && j < right) {
            if (sums[i] <= sums[j]) {
                temp[k++] = sums[i++];
            } else {
                temp[k++] = sums[j++];
            }
        }

        while (i < mid) {
            temp[k++] = sums[i++];
        }

        while (j < right) {
            temp[k++] = sums[j++];
        }

        System.arraycopy(temp, 0, sums, left, temp.length);

        return count;
    }
}
