import java.util.*;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0]; 
        });

        int[] lis = new int[envelopes.length];
        int size = 0;

        for (int[] envelope : envelopes) {
            int height = envelope[1];

            int left = 0, right = size;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (lis[mid] < height) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            lis[left] = height;
            if (left == size) {
                size++;
            }
        }

        return size;
    }
}
