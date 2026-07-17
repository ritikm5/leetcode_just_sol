class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> b - a);

        int time = 0;

        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];

            time += duration;
            maxHeap.offer(duration);

            if (time > lastDay) {
                time -= maxHeap.poll();
            }
        }

        return maxHeap.size();
    }
}
