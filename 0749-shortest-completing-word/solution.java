class Solution {

    public String shortestCompletingWord(String licensePlate, String[] words) {

        int[] need = new int[26];

        // Count required letters
        for (char ch : licensePlate.toCharArray()) {
            if (Character.isLetter(ch)) {
                need[Character.toLowerCase(ch) - 'a']++;
            }
        }

        String ans = null;

        for (String word : words) {

            int[] count = new int[26];

            for (char ch : word.toCharArray()) {
                count[ch - 'a']++;
            }

            if (isValid(need, count)) {
                if (ans == null || word.length() < ans.length()) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isValid(int[] need, int[] count) {

        for (int i = 0; i < 26; i++) {
            if (count[i] < need[i]) {
                return false;
            }
        }

        return true;
    }
}
