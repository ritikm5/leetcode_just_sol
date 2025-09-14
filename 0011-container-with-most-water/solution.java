class Solution {
    public int maxArea(int[] height) {
        int max = 0 ;
        int s=0; 
        int e=height.length-1;
        while (s<e){
            int area = Math.min(height[s], height[e]) * (e-s);
            if (max < area) max = area;

            if (height[s] >= height[e]) e--;
            else if (height[s] < height[e]) s++;
        }
        return max;
    }
}
