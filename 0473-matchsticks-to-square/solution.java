import java.util.Arrays;

class Solution {
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks == null || matchsticks.length < 4)
            return false;

        int sum = 0;
        for (int stick : matchsticks)
            sum += stick;

        if (sum % 4 != 0)
            return false;

        int target = sum / 4;

        // Sort in descending order
        Arrays.sort(matchsticks);
        reverse(matchsticks);

        // If the largest stick is bigger than one side
        if (matchsticks[0] > target)
            return false;

        int[] sides = new int[4];
        return dfs(matchsticks, 0, sides, target);
    }

    private boolean dfs(int[] matchsticks, int index, int[] sides, int target) {
        if (index == matchsticks.length) {
            return sides[0] == target &&
                   sides[1] == target &&
                   sides[2] == target &&
                   sides[3] == target;
        }

        int stick = matchsticks[index];

        for (int i = 0; i < 4; i++) {
            if (sides[i] + stick > target)
                continue;

            sides[i] += stick;

            if (dfs(matchsticks, index + 1, sides, target))
                return true;

            sides[i] -= stick;

            // Pruning:
            // If this side was empty and didn't work,
            // no need to try other empty sides.
            if (sides[i] == 0)
                break;
        }

        return false;
    }

    private void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
