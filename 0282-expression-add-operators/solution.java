class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", result);
        return result;
    }

    private void dfs(String num,
                     int target,
                     int index,
                     long value,
                     long prev,
                     String path,
                     List<String> result) {

        if (index == num.length()) {
            if (value == target) {
                result.add(path);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {

            if (i > index && num.charAt(index) == '0') {
                break;
            }

            String curStr = num.substring(index, i + 1);
            long cur = Long.parseLong(curStr);

            if (index == 0) {
                dfs(num, target, i + 1, cur, cur, curStr, result);
            } else {

                dfs(num, target,
                    i + 1,
                    value + cur,
                    cur,
                    path + "+" + curStr,
                    result);

                dfs(num, target,
                    i + 1,
                    value - cur,
                    -cur,
                    path + "-" + curStr,
                    result);

                dfs(num, target,
                    i + 1,
                    value - prev + prev * cur,
                    prev * cur,
                    path + "*" + curStr,
                    result);
            }
        }
    }
}
