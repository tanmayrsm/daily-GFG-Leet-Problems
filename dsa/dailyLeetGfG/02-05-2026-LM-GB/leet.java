class Solution {
    public int rotatedDigits(int n) {
        int ct = 0;
        for (int i = 1; i <= n; i++) {
            ct += isValid(i) ? 1 : 0;
        }
        return ct;
    }
    private boolean isValid(int n) {
        int nc = n;
        boolean iscand = false;
        while(n > 0) {
            int rem = n % 10;
            if(rem == 3 || rem == 4 || rem == 7)    return false;
            if(rem == 2 || rem == 5 || rem == 6 || rem == 9)    iscand = true;
            n /= 10;
        }
        return iscand;
    }
}