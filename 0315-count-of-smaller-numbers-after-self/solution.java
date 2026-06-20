import java.util.*;

class Solution {
    private int[] counts;
    private Pair[] temp;

    static class Pair {
        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;

        counts = new int[n];
        temp = new Pair[n];

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        mergeSort(arr, 0, n - 1);

        List<Integer> result = new ArrayList<>(n);
        for (int c : counts) {
            result.add(c);
        }

        return result;
    }

    private void mergeSort(Pair[] arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private void merge(Pair[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        int rightCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i].val <= arr[j].val) {
                counts[arr[i].idx] += rightCount;
                temp[k++] = arr[i++];
            } else {
                rightCount++;
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            counts[arr[i].idx] += rightCount;
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }
    }
}
