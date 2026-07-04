class Solution {
    public int compress(char[] chars) {
        int index = 0; // write pointer
        int i = 0;     // read pointer

        while (i < chars.length) {
            char current = chars[i];
            int count = 0;

            // Count consecutive characters
            while (i < chars.length && chars[i] == current) {
                i++;
                count++;
            }

            chars[index++] = current;

            if (count > 1) {
                String str = Integer.toString(count);
                for (char c : str.toCharArray()) {
                    chars[index++] = c;
                }
            }
        }

        return index;
    }
}
