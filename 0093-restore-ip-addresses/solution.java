import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int index, List<String> parts, List<String> result) {
        // If we already have 4 parts
        if (parts.size() == 4) {
            // Valid only if all characters are used
            if (index == s.length()) {
                result.add(String.join(".", parts));
            }
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String part = s.substring(index, index + len);

            if (isValid(part)) {
                parts.add(part);
                backtrack(s, index + len, parts, result);
                parts.remove(parts.size() - 1);
            }
        }
    }

    private boolean isValid(String part) {
        if (part.length() > 1 && part.charAt(0) == '0') {
            return false;
        }

        int num = Integer.parseInt(part);
        return num >= 0 && num <= 255;
    }
}
