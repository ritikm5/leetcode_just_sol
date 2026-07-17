class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]);

        int currentMax = Integer.MIN_VALUE;

        // Initialize heap
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            pq.offer(new int[]{val, i, 0}); // value, list index, element index
            currentMax = Math.max(currentMax, val);
        }

        int start = 0;
        int end = Integer.MAX_VALUE;

        while (pq.size() == nums.size()) {

            int[] cur = pq.poll();

            int value = cur[0];
            int list = cur[1];
            int index = cur[2];

            if (currentMax - value < end - start ||
                    (currentMax - value == end - start && value < start)) {
                start = value;
                end = currentMax;
            }

            if (index + 1 == nums.get(list).size())
                break;

            int next = nums.get(list).get(index + 1);

            pq.offer(new int[]{next, list, index + 1});
            currentMax = Math.max(currentMax, next);
        }

        return new int[]{start, end};
    }
}
