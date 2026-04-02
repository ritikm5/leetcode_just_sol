import java.util.*;

public class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return Collections.emptyList();

        Map<String, Integer> dist = new HashMap<>();
        Map<String, List<String>> parents = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dist.put(beginWord, 0);
        wordSet.add(beginWord);

        boolean found = false;
        int level = 0;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            level++;
            List<String> currentLevelWords = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                currentLevelWords.add(curr);

                for (String neigh : getNeighbors(curr, wordSet)) {
                    if (!dist.containsKey(neigh)) {
                        dist.put(neigh, level);
                        queue.offer(neigh);
                        parents.computeIfAbsent(neigh, k -> new ArrayList<>()).add(curr);
                   
                    } else if (dist.get(neigh) == level) {
                        parents.computeIfAbsent(neigh, k -> new ArrayList<>()).add(curr);
                    }

                    if (neigh.equals(endWord)) found = true;
                }
            }

            for (String w : currentLevelWords) wordSet.remove(w);
        }

        if (!dist.containsKey(endWord)) return Collections.emptyList();

        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String curr, String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path,
                     List<List<String>> result) {

        path.add(curr);

        if (curr.equals(beginWord)) {
            Collections.reverse(path);
            result.add(new ArrayList<>(path));
            Collections.reverse(path);
            path.remove(path.size() - 1);
            return;
        }

        if (parents.containsKey(curr)) {
            for (String prev : parents.get(curr)) dfs(prev, beginWord, parents, path, result);
        }
        path.remove(path.size() - 1);
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String candidate = new String(chars);
                if (wordSet.contains(candidate)) neighbors.add(candidate);
            }
            chars[i] = original;
        }
        return neighbors;
    }
}
