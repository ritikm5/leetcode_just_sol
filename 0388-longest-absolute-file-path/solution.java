class Solution {
    public int lengthLongestPath(String input) {

        String[] parts = input.split("\n");

        int[] pathLen = new int[parts.length + 1];
        int maxLen = 0;

        for (String part : parts) {

            int depth = 0;

            while (depth < part.length()
                    && part.charAt(depth) == '\t') {
                depth++;
            }

            String name = part.substring(depth);

            if (name.contains(".")) {
                maxLen = Math.max(
                    maxLen,
                    pathLen[depth] + name.length()
                );
            } else {
                pathLen[depth + 1] =
                    pathLen[depth] + name.length() + 1;
            }
        }

        return maxLen;
    }
}
