class Solution {

    class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;

        void add(int x) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            size++;
        }

        void remove(int x) {
            int cnt = map.get(x);
            if (cnt == 1) map.remove(x);
            else map.put(x, cnt - 1);
            size--;
        }

        int first() {
            return map.firstKey();
        }

        int last() {
            return map.lastKey();
        }

        boolean contains(int x) {
            return map.containsKey(x);
        }
    }

    MultiSet left = new MultiSet();   // smaller half
    MultiSet right = new MultiSet();  // larger half

    private void balance() {
        while (left.size > right.size + 1) {
            int x = left.last();
            left.remove(x);
            right.add(x);
        }

        while (left.size < right.size) {
            int x = right.first();
            right.remove(x);
            left.add(x);
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] ans = new double[nums.length - k + 1];

        int idx = 0;

        for (int i = 0; i < nums.length; i++) {

            if (left.size == 0 || nums[i] <= left.last())
                left.add(nums[i]);
            else
                right.add(nums[i]);

            balance();

            if (i >= k) {
                int out = nums[i - k];

                if (left.contains(out))
                    left.remove(out);
                else
                    right.remove(out);

                balance();
            }

            if (i >= k - 1) {
                if ((k & 1) == 1) {
                    ans[idx++] = left.last();
                } else {
                    ans[idx++] = ((long) left.last() + (long) right.first()) / 2.0;
                }
            }
        }

        return ans;
    }
}
