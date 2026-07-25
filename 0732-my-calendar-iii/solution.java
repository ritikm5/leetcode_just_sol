class MyCalendarThree {

    private TreeMap<Integer, Integer> map;

    public MyCalendarThree() {
        map = new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        map.put(startTime, map.getOrDefault(startTime, 0) + 1);
        map.put(endTime, map.getOrDefault(endTime, 0) - 1);

        int active = 0;
        int max = 0;

        for (int change : map.values()) {
            active += change;
            max = Math.max(max, active);
        }

        return max;
    }
}
