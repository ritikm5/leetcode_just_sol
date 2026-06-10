class Solution {
    public int findMin(int[] nums) {
        int left =0;
        int right = nums.length -1;
        while (left<right){
            if (nums[left] > nums[right]) left+=1;
            else if (nums[left]<nums[right]) right-=1;
        }
        return nums[left];
    }
}
