class Solution {
    public int maxPoints(int[][] points) {
        if (points.length <3){ return points.length; }
        int ans = 0;

        for (int i=0; i<points.length; i++){
            Map<String, Integer> map = new HashMap<>();
            int max =0;

            for (int j=i+1; j<points.length; j++){
                int dy = points[j][1] - points[i][1];
                int dx = points[j][0] - points[i][0];

                int g = gcd(dy, dx);
                dy /= g;
                dx /= g;

                if (dx<0){
                    dy = -dy;
                    dx = -dx;
                }
                else if (dx ==0){ dy =1; }
                else if (dy ==0){ dx =1; }

                String key = dy +"/"+ dx;
                int count = map.getOrDefault(key,0) + 1;
                map.put(key,count);

                max = Math.max(max, count);
            }
            ans = Math.max(ans, max+1);
        }
        return ans;
    }
    
    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }

        return a;
    }
}
