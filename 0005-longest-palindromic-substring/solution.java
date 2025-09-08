class Solution {

    public int expand(String S, int l, int r){
        while(l>=0 && r<S.length() && S.charAt(l) == S.charAt(r)){
            l--;
            r++;
        }
        return r-l-1;
    }

    public String longestPalindrome(String s) {

        int start = 0;
        int end =0;
        for(int i=0; i<s.length(); i++){
            int oddmaxlen = expand(s, i, i);
            int evenmaxlen = expand(s, i, i+1);

            int maxlen = Math.max(oddmaxlen, evenmaxlen);

            if (maxlen > end-start){
                start = i-(maxlen-1)/2;
                end = i+ maxlen/2;
            }
        }

        return s.substring(start, end+1);
    }
}
