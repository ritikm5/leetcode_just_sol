import java.util.*;

class Solution {
    public String longestWord(String[] words) {
        HashSet<String> set = new HashSet<>(Arrays.asList(words));

        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length(); // longer words first
            }
            return a.compareTo(b); // lexicographically smaller first
        });

        for (String word : words) {
            boolean valid = true;

            for (int i = 1; i < word.length(); i++) {
                if (!set.contains(word.substring(0, i))) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                return word;
            }
        }

        return "";
    }
}

