import java.util.*;

class SummaryRanges {

    private TreeMap<Integer, int[]> intervals;

    public SummaryRanges() {
        intervals = new TreeMap<>();
    }

    public void addNum(int value) {
        if (intervals.containsKey(value)) return;

        Integer lowerKey = intervals.floorKey(value);
        Integer higherKey = intervals.ceilingKey(value);

        if (lowerKey != null && intervals.get(lowerKey)[1] >= value) {
            return;
        }

        boolean mergeLeft = lowerKey != null && intervals.get(lowerKey)[1] + 1 == value;
        boolean mergeRight = higherKey != null && higherKey - 1 == value;

        if (mergeLeft && mergeRight) {
            intervals.get(lowerKey)[1] = intervals.get(higherKey)[1];
            intervals.remove(higherKey);
        } else if (mergeLeft) {
            intervals.get(lowerKey)[1] = value;
        } else if (mergeRight) {
            int end = intervals.get(higherKey)[1];
            intervals.remove(higherKey);
            intervals.put(value, new int[]{value, end});
        } else {
            intervals.put(value, new int[]{value, value});
        }
    }

    public int[][] getIntervals() {
        return intervals.values().toArray(new int[intervals.size()][]);
    }
}
