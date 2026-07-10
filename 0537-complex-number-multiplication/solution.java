class Solution {
    public String complexNumberMultiply(String num1, String num2) {
        int[] c1 = parse(num1);
        int[] c2 = parse(num2);

        int a = c1[0], b = c1[1];
        int c = c2[0], d = c2[1];

        int real = a * c - b * d;
        int imag = a * d + b * c;

        return real + "+" + imag + "i";
    }

    private int[] parse(String s) {
        s = s.substring(0, s.length() - 1); 
        String[] parts = s.split("\\+");

        int real = Integer.parseInt(parts[0]);
        int imag = Integer.parseInt(parts[1]);

        return new int[]{real, imag};
    }
}
