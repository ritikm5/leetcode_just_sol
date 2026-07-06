class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;

        int len1 = s1.length();
        int len2 = s2.length();

        Map<Integer, int[]> map = new HashMap<>();

        int s1Count = 0;
        int s2Count = 0;
        int index = 0;

        while (true) {
            s1Count++;

            // Scan one copy of s1
            for (int i = 0; i < len1; i++) {
                if (s1.charAt(i) == s2.charAt(index)) {
                    index++;
                    if (index == len2) {
                        s2Count++;
                        index = 0;
                    }
                }
            }

            // Processed all copies of s1
            if (s1Count == n1) {
                return s2Count / n2;
            }

            // Cycle detected
            if (map.containsKey(index)) {
                int[] prev = map.get(index);

                int preS1 = prev[0];
                int preS2 = prev[1];

                int loopS1 = s1Count - preS1;
                int loopS2 = s2Count - preS2;

                int remaining = n1 - preS1;

                int loops = remaining / loopS1;

                int ans = preS2 + loops * loopS2;

                int rest = remaining % loopS1;

                // Simulate the remaining copies
                index = 0;
                s2Count = 0;

                for (int k = 0; k < preS1 + rest; k++) {
                    for (int i = 0; i < len1; i++) {
                        if (s1.charAt(i) == s2.charAt(index)) {
                            index++;
                            if (index == len2) {
                                s2Count++;
                                index = 0;
                            }
                        }
                    }
                }

                return (ans + (s2Count - preS2)) / n2;
            } else {
                map.put(index, new int[]{s1Count, s2Count});
            }
        }
    }
}
