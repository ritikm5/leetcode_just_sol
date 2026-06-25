import java.util.Random;

class Solution {

    private int[] original;
    private Random rand;

    public Solution(int[] nums) {
        original = nums.clone();
        rand = new Random();
    }

    public int[] reset() {
        return original.clone();
    }

    public int[] shuffle() {

        int[] shuffled = original.clone();

        for (int i = 0; i < shuffled.length; i++) {

            int j = i + rand.nextInt(shuffled.length - i);

            int temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }

        return shuffled;
    }
}
