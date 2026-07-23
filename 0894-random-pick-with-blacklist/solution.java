import java.util.*;

class Solution {

    private Map<Integer, Integer> map;
    private Random random;
    private int size;

    public Solution(int n, int[] blacklist) {
        map = new HashMap<>();
        random = new Random();

        size = n - blacklist.length;

        // Mark all blacklisted numbers
        Set<Integer> black = new HashSet<>();
        for (int b : blacklist) {
            black.add(b);
        }

        int last = n - 1;

        for (int b : blacklist) {

            // Only remap blacklisted numbers in [0, size-1]
            if (b < size) {

                while (black.contains(last)) {
                    last--;
                }

                map.put(b, last);
                last--;
            }
        }
    }

    public int pick() {
        int x = random.nextInt(size);

        if (map.containsKey(x)) {
            return map.get(x);
        }

        return x;
    }
}

