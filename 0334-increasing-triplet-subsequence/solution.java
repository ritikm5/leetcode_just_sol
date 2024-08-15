class Solution {
    public boolean increasingTriplet(int[] nums) {

        Set<Integer> set = new HashSet<Integer> (); 
        for (int j=0; j<nums.length; j++){
            set.add(nums[j]);
        }

        for (int j=0; j<nums.length-2 && set.size()>2; j++){
            for (int i=j+1; i<nums.length-1; i++){
                if (nums[j] < nums[i]){
                    for (int k=i+1; k<nums.length; k++){
                        if(nums[i] < nums[k]){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
