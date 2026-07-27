class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;

        // position[person] = current seat
        int[] position = new int[n];
        for (int i = 0; i < n; i++) {
            position[row[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i += 2) {
            int first = row[i];
            int partner = first ^ 1;

            if (row[i + 1] == partner)
                continue;


            swaps++;
            int partnerPos = position[partner];
            int second = row[i + 1];
            row[partnerPos] = second;
            row[i + 1] = partner;
            position[second] = partnerPos;
            position[partner] = i + 1;
        }

        return swaps;
    }
}
