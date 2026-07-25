class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {

            boolean alive = true;

            while (alive &&
                   asteroid < 0 &&
                   !stack.isEmpty() &&
                   stack.peek() > 0) {

                if (stack.peek() < -asteroid) {
                    stack.pop();
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                    alive = false;
                } else {
                    alive = false;
                }
            }

            if (alive) {
                stack.push(asteroid);
            }
        }

        int[] ans = new int[stack.size()];
        for (int i = ans.length - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}
