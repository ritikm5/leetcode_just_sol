class Solution {
    public boolean validUtf8(int[] data) {
        int remaining = 0;

        for (int b : data) {
            b &= 0xFF;

            if (remaining == 0) {

                if ((b & 0b10000000) == 0) {
                    // 1-byte character
                    continue;
                } else if ((b & 0b11100000) == 0b11000000) {
                    remaining = 1;
                } else if ((b & 0b11110000) == 0b11100000) {
                    remaining = 2;
                } else if ((b & 0b11111000) == 0b11110000) {
                    remaining = 3;
                } else {
                    return false;
                }

            } else {

                if ((b & 0b11000000) != 0b10000000)
                    return false;

                remaining--;
            }
        }

        return remaining == 0;
    }
}
