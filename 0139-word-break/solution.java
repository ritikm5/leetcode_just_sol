import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        
        // dp[i] = true if substring s[0...i-1] can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // Check if left part is valid and right part exists in dictionary
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.wordBreak("leetcode", Arrays.asList("leet", "code"))
        );

        System.out.println(
            sol.wordBreak("applepenapple", Arrays.asList("apple", "pen"))
        );

        System.out.println(
            sol.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"))
        ); 
    }
}
