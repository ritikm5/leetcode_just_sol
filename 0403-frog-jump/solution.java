import java.util.*;

class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Initialize map for every stone
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }

        // First jump must be 1
        map.get(0).add(1);

        int lastStone = stones[stones.length - 1];

        for (int stone : stones) {
            for (int jump : map.get(stone)) {
                int nextStone = stone + jump;

                if (nextStone == lastStone) {
                    return true;
                }

                if (map.containsKey(nextStone)) {
                    if (jump - 1 > 0) {
                        map.get(nextStone).add(jump - 1);
                    }
                    map.get(nextStone).add(jump);
                    map.get(nextStone).add(jump + 1);
                }
            }
        }

        return false;
    }
}
