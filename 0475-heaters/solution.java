import java.util.Arrays;

class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);

        int answer = 0;

        for (int house : houses) {
            int index = binarySearch(heaters, house);

            int distance = Integer.MAX_VALUE;

            if (index < heaters.length) {
                distance = heaters[index] - house;
            }

            if (index > 0) {
                distance = Math.min(distance, house - heaters[index - 1]);
            }

            answer = Math.max(answer, distance);
        }

        return answer;
    }

    private int binarySearch(int[] heaters, int house) {
        int left = 0;
        int right = heaters.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (heaters[mid] < house) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
