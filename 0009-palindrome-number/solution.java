class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        if (s.length() <=1 ) return true;
        for (int i = 0; i<s.length()/2; i++){
            if (s.charAt(i) != s.charAt(s.length()-i-1)) return false;
        }
        return true;
    }
}
