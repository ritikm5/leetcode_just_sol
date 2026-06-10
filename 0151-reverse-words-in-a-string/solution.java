class Solution {
    public String reverseWords(String s) {
        String ans = "";
        String current = "";
        for (char c : s.trim().toCharArray()){
            if (c == ' '){ 
                ans = current + " "+ ans.trim(); 
                current = "";
            }
            else{
                current += c;
            }
        }

        ans = current + " "+ ans.trim(); 
        return ans.trim();
    }
}


