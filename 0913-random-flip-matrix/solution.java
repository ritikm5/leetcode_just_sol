class Solution {

    private int rows;
    private int cols;
    private int total;
    private Map<Integer, Integer> map;
    private Random rand;

    public Solution(int m, int n) {
        rows = m;
        cols = n;
        total = m * n;
        map = new HashMap<>();
        rand = new Random();
    }

    public int[] flip() {
        int r = rand.nextInt(total);
        int index = map.getOrDefault(r, r);
        total--;
        map.put(r, map.getOrDefault(total, total));

        return new int[]{index / cols, index % cols};
    }

    public void reset() {
        map.clear();
        total = rows * cols;
    }
}
