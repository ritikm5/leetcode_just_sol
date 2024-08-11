class Solution {
    public String mergeAlternately(String word1, String word2) {
        String large = word1;
        String small = word2;
        String result = "";
        if (large.length() < small.length()) {
            large = small;
            small = word1;
        }
        for (int j = 0; j < small.length(); j++) {
            result += word1.charAt(j);
            result += word2.charAt(j);
        }
        result += large.substring(small.length());

        return result;
    }
}
