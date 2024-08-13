class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> result = new ArrayList<Boolean>(candies.length);

        int max = candies[0];
        for (int j =0; j < candies.length; j++){
            if (candies[j]>max){
                max = candies[j];
            }
        }

        for (int j =0; j<candies.length; j++){
            boolean good = false;
            if (candies[j]+extraCandies >= max){
                good = true;
            }
            result.add(good);
        }

        return result;
    }
}
