class Solution {

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 0)
                continue;

            boolean forward = nums[i] > 0;

            int slow = i;
            int fast = i;

            // Floyd's Cycle Detection
            while (true) {

                slow = next(nums, forward, slow);
                if (slow == -1)
                    break;

                fast = next(nums, forward, fast);
                if (fast == -1)
                    break;

                fast = next(nums, forward, fast);
                if (fast == -1)
                    break;

                if (slow == fast)
                    return true;
            }

            // Mark only nodes in the same direction
            int curr = i;
            while ((nums[curr] > 0) == forward) {

                int nxt = ((curr + nums[curr]) % n + n) % n;
                nums[curr] = 0;

                if (nxt == curr)
                    break;

                curr = nxt;
            }
        }

        return false;
    }

    private int next(int[] nums, boolean forward, int curr) {

        if ((nums[curr] > 0) != forward)
            return -1;

        int n = nums.length;
        int nxt = ((curr + nums[curr]) % n + n) % n;

        if (nxt == curr)
            return -1;

        if ((nums[nxt] > 0) != forward)
            return -1;

        return nxt;
    }
}
