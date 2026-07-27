import java.util.Arrays;

class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0]; // start descending if end is same
            }
            return a[1] - b[1];     // end ascending
        });

        int ans = 0;
        int first = -1, second = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start <= first) {
                continue;
            } else if (start <= second) {
                ans++;
                first = second;
                second = end;
            } else {
                ans += 2;
                first = end - 1;
                second = end;
            }
        }

        return ans;
    }
}
