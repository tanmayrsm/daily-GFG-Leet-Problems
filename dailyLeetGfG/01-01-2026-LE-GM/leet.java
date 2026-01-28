class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length, carry = 0;
        int[] ans = new int[n];
        ans[n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int dig = digits[i] + ans[i] + carry;
            if (dig > 9) {
                ans[i] = dig % 10;
                carry = 1;
            } else {
                ans[i] = dig;
                carry = 0;
            }
        }
        if (carry == 1) {
            ans = new int[n + 1];
            ans[0] = 1;
        }
        return ans;
    }
}