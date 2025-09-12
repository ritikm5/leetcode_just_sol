class Solution {
    public int reverse(int x) {
        if (x == 0) return 0;

        String s = String.valueOf(x);
        String sx ="";
        for (int i=0; i<s.length();i++){
            sx+= String.valueOf(s.charAt(s.length()-i-1));
        }
        if (sx.contains("-")) sx = "-" + sx.substring(0, sx.length()-1);
        if (Math.pow(-2.0, 31.0) > Long.parseLong(sx) ||  Long.parseLong(sx) > (Math.pow(2.0, 31.0) -1)) return 0;
        return Integer.valueOf(sx);
    }
}
