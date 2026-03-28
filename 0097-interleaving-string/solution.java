class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        if (s1.length() > s2.length()) {
            return isInterleave(s2, s1, s3);
        }
        
        int m = s1.length();  
        int n = s2.length();
        
        boolean[] dp = new boolean[m + 1];
        
        dp[0] = true;
        
        for (int i = 1; i <= m; i++) {
            dp[i] = dp[i - 1] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int j = 1; j <= n; j++) {
            dp[0] = dp[0] && (s2.charAt(j - 1) == s3.charAt(j - 1));
            
            for (int i = 1; i <= m; i++) {
                int k = i + j - 1; 
                boolean fromS1 = dp[i - 1] && (s1.charAt(i - 1) == s3.charAt(k));
                boolean fromS2 = dp[i] && (s2.charAt(j - 1) == s3.charAt(k));       
                dp[i] = fromS1 || fromS2;
            }
        }
        return dp[m];
    }
}
