class Solution {
    public String[] findRelativeRanks(int[] score) {

        int n = score.length;
        String[] ans = new String[n];

        int[][] athletes = new int[n][2];

        for (int i = 0; i < n; i++) {
            athletes[i][0] = score[i];
            athletes[i][1] = i;
        }

        Arrays.sort(athletes, (a, b) -> Integer.compare(b[0], a[0]));

        for (int i = 0; i < n; i++) {
            int idx = athletes[i][1];

            if (i == 0) {
                ans[idx] = "Gold Medal";
            } else if (i == 1) {
                ans[idx] = "Silver Medal";
            } else if (i == 2) {
                ans[idx] = "Bronze Medal";
            } else {
                ans[idx] = String.valueOf(i + 1);
            }
        }

        return ans;
    }
}
