class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;

        Arrays.sort(nums);

        // Reverse to descending order
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        if (nums[0] > target) {
            return false;
        }

        int[] buckets = new int[k];
        return backtrack(nums, 0, buckets, target);
    }

    private boolean backtrack(int[] nums, int index, int[] buckets, int target) {
        if (index == nums.length) {
            return true;
        }

        int num = nums[index];

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + num > target) {
                continue;
            }

            buckets[i] += num;

            if (backtrack(nums, index + 1, buckets, target)) {
                return true;
            }

            buckets[i] -= num;

            // Optimization:
            // If this bucket was empty before placing the number,
            // no need to try other empty buckets.
            if (buckets[i] == 0) {
                break;
            }
        }

        return false;
    }
}

