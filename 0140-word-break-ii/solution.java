public class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        // Memoization map
        Map<String, List<String>> memo = new HashMap<>();

        return dfs(s, dict, memo);
    }

    private List<String> dfs(String s, Set<String> dict,
                             Map<String, List<String>> memo) {

        // If already computed
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        // Base case
        if (s.length() == 0) {
            result.add("");
            return result;
        }

        for (String word : dict) {

            // Check if current string starts with word
            if (s.startsWith(word)) {

                String remaining = s.substring(word.length());

                List<String> subSentences = dfs(remaining, dict, memo);

                for (String sub : subSentences) {

                    // Avoid extra space at end
                    String sentence = word +
                            (sub.isEmpty() ? "" : " " + sub);

                    result.add(sentence);
                }
            }
        }

        memo.put(s, result);

        return result;
    }

    public static void main(String[] args) {

        Solution sol = new Solution();

        System.out.println(
                sol.wordBreak(
                        "catsanddog",
                        Arrays.asList("cat", "cats", "and", "sand", "dog")
                )
        );

        System.out.println(
                sol.wordBreak(
                        "pineapplepenapple",
                        Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")
                )
        );

        System.out.println(
                sol.wordBreak(
                        "catsandog",
                        Arrays.asList("cats", "dog", "sand", "and", "cat")
                )
        );
    }
}
