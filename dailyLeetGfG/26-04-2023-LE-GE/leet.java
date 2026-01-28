class Solution {
    public int addDigits(int num) {
        String s = String.valueOf(num);
        while(true) {
            num = summer(s);
            if(num < 10)
                return num;
            s = String.valueOf(num);
        }
    }
    private static int summer(String s) {
        int sum = 0;
        for(int i = 0; i < s.length(); i++)
            sum += s.charAt(i) - '0';
        return sum;
    }
}