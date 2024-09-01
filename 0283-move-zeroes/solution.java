class Solution {
    private static void arange(int[] result) {
        for(int j=0; j<result.length; j++){
            if (result[j] == 0){
                int temp = result[j];
                if (j+1<result.length){
                    if (result[j+1] != 0){
                        result[j] = result[j+1];
                        result[j+1] = temp;
                    }
                }
            }
        }
    }

    public void moveZeroes(int[] nums) {
        for(int j=0; j<nums.length; j++){
            arange(nums);
        }
    }
}
