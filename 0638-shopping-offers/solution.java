class Solution {

    Map<List<Integer>, Integer> memo = new HashMap<>();

    public int shoppingOffers(List<Integer> price,
                              List<List<Integer>> special,
                              List<Integer> needs) {

        return dfs(price, special, needs);
    }

    private int dfs(List<Integer> price,
                    List<List<Integer>> special,
                    List<Integer> needs) {

        if (memo.containsKey(needs))
            return memo.get(needs);

        int n = price.size();

        // Buy everything individually
        int minCost = 0;
        for (int i = 0; i < n; i++) {
            minCost += needs.get(i) * price.get(i);
        }

        // Try every special offer
        for (List<Integer> offer : special) {

            List<Integer> remaining = new ArrayList<>();
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if (offer.get(i) > needs.get(i)) {
                    valid = false;
                    break;
                }

                remaining.add(needs.get(i) - offer.get(i));
            }

            if (valid) {
                minCost = Math.min(minCost,
                        offer.get(n) + dfs(price, special, remaining));
            }
        }

        memo.put(needs, minCost);
        return minCost;
    }
}
