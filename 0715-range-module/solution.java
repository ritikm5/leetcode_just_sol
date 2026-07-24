import java.util.*;

class RangeModule {
    private List<int[]> intervals;

    public RangeModule() {
        intervals = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        List<int[]> res = new ArrayList<>();
        int i = 0;

        // Add intervals completely before the new range
        while (i < intervals.size() && intervals.get(i)[1] < left) {
            res.add(intervals.get(i));
            i++;
        }

        // Merge overlapping intervals
        while (i < intervals.size() && intervals.get(i)[0] <= right) {
            left = Math.min(left, intervals.get(i)[0]);
            right = Math.max(right, intervals.get(i)[1]);
            i++;
        }

        res.add(new int[]{left, right});

        // Add remaining intervals
        while (i < intervals.size()) {
            res.add(intervals.get(i));
            i++;
        }

        intervals = res;
    }

    public boolean queryRange(int left, int right) {
        int l = 0, r = intervals.size() - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int[] interval = intervals.get(mid);

            if (interval[0] <= left) {
                if (interval[1] >= right) {
                    return true;
                }
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }

    public void removeRange(int left, int right) {
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            if (interval[1] <= left || interval[0] >= right) {
                res.add(interval);
            } else {
                if (interval[0] < left) {
                    res.add(new int[]{interval[0], left});
                }
                if (interval[1] > right) {
                    res.add(new int[]{right, interval[1]});
                }
            }
        }

        intervals = res;
    }
}

