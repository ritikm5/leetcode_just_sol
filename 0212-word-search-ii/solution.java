import java.util.*;

class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word; 
    }

    private List<String> result = new ArrayList<>();
    private int rows, cols;

    public List<String> findWords(char[][] board, String[] words) {

        TrieNode root = buildTrie(words);

        rows = board.length;
        cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode curr = root;

            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';

                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }

                curr = curr.children[idx];
            }

            curr.word = word;
        }

        return root;
    }

    private void dfs(char[][] board, int r, int c, TrieNode node) {

        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            return;
        }

        char ch = board[r][c];

        if (ch == '#') {
            return;
        }

        node = node.children[ch - 'a'];

        if (node == null) {
            return;
        }

        if (node.word != null) {
            result.add(node.word);
            node.word = null; 
        }

        board[r][c] = '#';

        dfs(board, r + 1, c, node);
        dfs(board, r - 1, c, node);
        dfs(board, r, c + 1, node);
        dfs(board, r, c - 1, node);

        board[r][c] = ch;
    }
}
