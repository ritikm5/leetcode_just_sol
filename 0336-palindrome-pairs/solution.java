import java.util.*;

class Solution {

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int index = -1;
        List<Integer> palindromeSuffixes = new ArrayList<>();
    }

    private TrieNode root = new TrieNode();

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words[i], i, result);
        }

        return result;
    }

    private void insert(String word, int index) {
        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            if (isPalindrome(word, 0, i)) {
                node.palindromeSuffixes.add(index);
            }

            int c = word.charAt(i) - 'a';

            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }

            node = node.children[c];
        }

        node.index = index;
        node.palindromeSuffixes.add(index);
    }

    private void search(String word, int index,
                        List<List<Integer>> result) {

        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {

            if (node.index >= 0 &&
                node.index != index &&
                isPalindrome(word, i, word.length() - 1)) {

                result.add(Arrays.asList(index, node.index));
            }

            int c = word.charAt(i) - 'a';

            if (node.children[c] == null) {
                return;
            }

            node = node.children[c];
        }

        for (int other : node.palindromeSuffixes) {
            if (other != index) {
                result.add(Arrays.asList(index, other));
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
