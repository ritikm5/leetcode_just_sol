import java.util.*;

public class Solution {
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        
        // Mapping of digits to letters (same as telephone keypad)
        String[] phoneMap = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };
        
        backtrack(digits, 0, new StringBuilder(), result, phoneMap);
        return result;
    }
    
    private void backtrack(String digits, int index, StringBuilder path, 
                           List<String> result, String[] phoneMap) {
        
        if (index == digits.length()) {
            result.add(path.toString());
            return;
        }
        String letters = phoneMap[digits.charAt(index) - '0'];
        
        for (char letter : letters.toCharArray()) {
            path.append(letter);          
            backtrack(digits, index + 1, path, result, phoneMap);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
