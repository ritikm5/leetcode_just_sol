
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> big = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<nums.length-3;i++){
            if (i>0 && nums[i] == nums[i-1]) continue;

            for (int j=i+1; j<nums.length-2; j++){
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                
                int l=j+1;
                int r = nums.length-1;
                while(l<r){
                    long sum = (long) nums[i]+nums[j]+nums[l]+nums[r];
                    if (sum == target){ 
                        List<Integer> small = new ArrayList<>();
                        small.add(nums[i]);
                        small.add(nums[l]);
                        small.add(nums[r]);
                        small.add(nums[j]);
                        big.add(small);
                    
                        while (l<r && nums[r] == nums[r-1]) r--;
                        while (l<r && nums[l] == nums[l+1]) l++;   
                        l++;
                        r--;      
                    }
                    else if (sum>target) r--;
                    else l++;
                }
            }
        }
        return big;
    }
}
