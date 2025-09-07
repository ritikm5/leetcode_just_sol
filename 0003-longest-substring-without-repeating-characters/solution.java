class Solution {

    public boolean contains(String S, char C){
        for (int Ci = 0; Ci < S.length(); Ci++){
            if (C == S.charAt(Ci)) return true;
        }
        return false;
    }

    public String droptill(String S, char C){
        if (S.charAt(S.length()-1) == C) return "";
        String Sr=S;
        while (contains(Sr, C)){
            Sr = Sr.substring(1);
        }
        return Sr;
    }

    public int lengthOfLongestSubstring(String s) {

        String s1 = "";
        int maxlen =0;
        if (s.length() == 1){
            return 1;
        } 
        else{
            for (int i=0; i<s.length(); i++){    // pwwkew  //dvdf
                char c1 = s.charAt(i);
                if (contains(s1, c1)){
                    if (maxlen < s1.length()) maxlen = s1.length();
                    s1 =  droptill(s1,c1) + c1;
                }
                else{
                    s1+=c1; 
                }
            }
        }
        return Math.max(maxlen, s1.length());
    }
}
