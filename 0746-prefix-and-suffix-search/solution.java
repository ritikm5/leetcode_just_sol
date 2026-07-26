import java.util.*;

class WordFilter {

    private Map<String, Integer> map;

    public WordFilter(String[] words) {
        map = new HashMap<>();

        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            int n = word.length();

            for (int i = 0; i <= n; i++) {
                String prefix = word.substring(0, i);

                for (int j = 0; j <= n; j++) {
                    String suffix = word.substring(j);
                    map.put(prefix + "#" + suffix, index);
                }
            }
        }
    }

    public int f(String pref, String suff) {
        return map.getOrDefault(pref + "#" + suff, -1);
    }
}
