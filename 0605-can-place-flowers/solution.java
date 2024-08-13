class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int j = 0; j<flowerbed.length && n!=0; j++){
            if (flowerbed.length==1 && flowerbed[j]==0){
                n =0;
            } else {
                if (j==0){
                    if (flowerbed[j] == 0 && flowerbed[j+1] == 0){
                        flowerbed[j] = 1;
                        n-=1;
                    }
                } else if (j==flowerbed.length-1){
                    if (flowerbed[j] == 0 && flowerbed[j-1] == 0){
                        flowerbed[j] = 1;
                        n-=1;
                    }   
                } else{
                    if(flowerbed[j] ==0 && flowerbed[j-1]==0 && flowerbed[j+1]==0){
                        flowerbed[j] = 1;
                        n-=1;
                    }  
                }
            }
        }
        
        return n==0;
    }
}
