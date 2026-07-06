class Solution {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>();
        for (String w : words) {
            set.add(w);
        }

        List<String> ans = new ArrayList<>();

        for (String word : words) {
            set.remove(word);

            Map<String, Boolean> memo = new HashMap<>();
            if (canForm(word, set, memo)) {
                ans.add(word);
            }

            set.add(word);
        }

        return ans;
    }

    private boolean canForm(String word, Set<String> set,
                            Map<String, Boolean> memo) {

        if (word.length() == 0)
            return true;

        if (memo.containsKey(word))
            return memo.get(word);

        for (int i = 1; i <= word.length(); i++) {
            String prefix = word.substring(0, i);

            if (set.contains(prefix) &&
                canForm(word.substring(i), set, memo)) {

                memo.put(word, true);
                return true;
            }
        }

        memo.put(word, false);
        return false;
    }
}
