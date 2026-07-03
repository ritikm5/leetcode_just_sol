import java.util.*;

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }

        for (int i = 0; i < n; i++) {
            Map.Entry<Integer, Integer> entry = map.ceilingEntry(intervals[i][1]);

            if (entry == null) {
                ans[i] = -1;
            } else {
                ans[i] = entry.getValue();
            }
        }

        return ans;
    }
}
