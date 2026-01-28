class Solution {
    public boolean divby13(String s) {
        // code here
        int n = s.length();
        if (n == 1) return false;
        int curr = (int)(s.charAt(0) - '0');
        for (int i = 1; i < n; i++) {
            curr = curr * 10 + (int)(s.charAt(i) - '0');
            curr = curr % 13;
        }
        return curr == 0;

    }
}

//Examples:
//
//Input : s = "2911285"
//Output : true
//Explanation: 2911285 ÷ 13 = 223945, which is a whole number with no remainder.
//Input : s = "27"
//Output : false
//Explanation: 27 / 13 ≈ 2.0769..., which is not a whole number (there is a remainder).
//
//29 % 13 = 2, rem -> 3
//(3)1 % 13 = 2, rem -> 5
//(5)1 % 13 =
//
//
//241
//curr = 24
//temp = 11
//
//curr = 111
//temp = 7
