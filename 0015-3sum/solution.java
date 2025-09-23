
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> big = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<nums.length-1;i++){
            
            if (i>0 && nums[i] == nums[i-1]) continue;

            int l=i+1;
            int r = nums.length-1;

            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if (sum == 0){ 
                    List<Integer> small = new ArrayList<>();
                    small.add(nums[i]);
                    small.add(nums[l]);
                    small.add(nums[r]);
                    //if (!big.contains(small)) 
                    big.add(small);
                    
                    while (l<r && nums[r] == nums[r-1]) r--;
                    while (l<r && nums[l] == nums[l+1]) l++;   
                    
                    l++;
                    r--;         
                }
                else if (sum>0) r--;
                else if (sum<0) l++;
            }
        }
        return big;
    }
}
