import java.util.*;

class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;

        int[] heights = new int[n];
        List<Integer> ans = new ArrayList<>();

        int maxHeight = 0;

        for (int i = 0; i < n; i++) {

            int left = positions[i][0];
            int side = positions[i][1];
            int right = left + side;

            int baseHeight = 0;

            // Check all previous squares
            for (int j = 0; j < i; j++) {

                int prevLeft = positions[j][0];
                int prevRight = prevLeft + positions[j][1];

                if (left < prevRight && right > prevLeft) {
                    baseHeight = Math.max(baseHeight, heights[j]);
                }
            }

            heights[i] = baseHeight + side;

            maxHeight = Math.max(maxHeight, heights[i]);
            ans.add(maxHeight);
        }

        return ans;
    }
}

