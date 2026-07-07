class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> path) {
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }

        Set<Integer> used = new HashSet<>();

        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }

            if (path.size() > 0 && nums[i] < path.get(path.size() - 1)) {
                continue;
            }

            used.add(nums[i]);
            path.add(nums[i]);

            backtrack(nums, i + 1, path);

            path.remove(path.size() - 1);
        }
    }
}
