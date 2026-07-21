class Solution {
    public boolean checkValidString(String s) {
        int low = 0;  // Minimum possible number of unmatched '('
        int high = 0; // Maximum possible number of unmatched '('

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { 
                low--;   
                high++;  
            }
            if (high < 0) {
                return false;
            }
            if (low < 0) {
                low = 0;
            }
        }
        return low == 0;
    }
}

