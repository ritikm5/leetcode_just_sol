class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][] {newInterval};
        }
        
        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        int i = 0;
        
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        int[][] resArray = new int[result.size()][2];
        for (int j = 0; j < result.size(); j++) resArray[j] = result.get(j);
        
        return resArray;
    }
}
