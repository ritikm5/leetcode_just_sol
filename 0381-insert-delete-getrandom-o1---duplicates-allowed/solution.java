import java.util.*;

class RandomizedCollection {

    private List<Integer> nums;
    private Map<Integer, Set<Integer>> map;
    private Random random;

    public RandomizedCollection() {
        nums = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean notPresent =
                !map.containsKey(val) || map.get(val).isEmpty();

        map.computeIfAbsent(val, k -> new HashSet<>())
           .add(nums.size());

        nums.add(val);

        return notPresent;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).isEmpty()) {
            return false;
        }

        int removeIdx = map.get(val).iterator().next();
        int lastIdx = nums.size() - 1;
        int lastVal = nums.get(lastIdx);
        map.get(val).remove(removeIdx);

        if (removeIdx != lastIdx) {
            nums.set(removeIdx, lastVal);
            map.get(lastVal).remove(lastIdx);
            map.get(lastVal).add(removeIdx);
        }

        nums.remove(lastIdx);
        return true;
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}
