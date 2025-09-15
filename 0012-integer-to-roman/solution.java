class Solution {
    public String intToRoman(int num) {
        String re ="";
        while (num/1000 > 0){
            re+= "M";
            num -=1000;
        }
        while (num/900 > 0){
            re+= "CM";
            num -=900;
        }
        while (num/500 > 0){
            re+= "D";
            num -=500;
        }
        while (num/400 > 0){
            re+= "CD";
            num -=400;
        }
        while (num/100 > 0){
            re+= "C";
            num -=100;
        }
        while (num/90 > 0){
            re+= "XC";
            num -=90;
        }
        while (num/50 > 0){
            re+= "L";
            num -=50;
        }
        while (num/40 > 0){
            re+= "XL";
            num -=40;
        }
        while (num/10 > 0){
            re+= "X";
            num -=10;
        }
        while (num/9 > 0){
            re+= "IX";
            num -=9;
        }
        while (num/5 > 0){
            re+= "V";
            num -=5;
        }
        while (num/4 > 0){
            re+= "IV";
            num -=4;
        }
        while (num > 0){
            re+= "I";
            num -=1;
        }
        return re;
    }
}
