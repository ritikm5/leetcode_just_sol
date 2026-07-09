class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";

        for (String word : dictionary) {
            if (isSubsequence(word, s)) {
                if (word.length() > ans.length() ||
                   (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    private boolean isSubsequence(String word, String s) {
        int i = 0, j = 0;

        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == word.length();
    }
}
