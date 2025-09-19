class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i =0; i<nums.length -2; i++){
            int first = nums[i];
            int left = i+1;
            int right = nums.length -1;

            while (left<right){
                int curent = first + nums[left] + nums[right];
                if (Math.abs(curent - target) < Math.abs(result - target)) result = curent;

                if (curent < target) left++;
                else if (curent > target) right--;
                else return curent;
            }
        }
        return result;
    }
}
