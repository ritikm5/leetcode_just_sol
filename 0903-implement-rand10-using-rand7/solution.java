/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int row = rand7();
            int col = rand7();

            int num = (row - 1) * 7 + col; // 1..49

            if (num <= 40) {
                return (num - 1) % 10 + 1;
            }
        }
    }
}
