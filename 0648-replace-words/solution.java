class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    TrieNode root = new TrieNode();

    // Insert root word into Trie
    private void insert(String word) {
        TrieNode node = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }

        node.isEnd = true;
    }

    // Find shortest root for a word
    private String search(String word) {
        TrieNode node = root;
        StringBuilder prefix = new StringBuilder();

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (node.children[idx] == null) {
                return word;
            }

            prefix.append(ch);
            node = node.children[idx];

            if (node.isEnd) {
                return prefix.toString();
            }
        }

        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        for (String word : dictionary) {
            insert(word);
        }

        String[] words = sentence.split(" ");
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
            ans.append(search(word)).append(" ");
        }

        ans.setLength(ans.length() - 1); 
        return ans.toString();
    }
}
