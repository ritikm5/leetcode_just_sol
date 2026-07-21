import java.util.HashMap;

class MapSum {
    private HashMap<String, Integer> map;

    public MapSum() {
        map = new HashMap<>();
    }

    public void insert(String key, int val) {
        map.put(key, val);
    }

    public int sum(String prefix) {
        int result = 0;

        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                result += map.get(key);
            }
        }

        return result;
    }
}

