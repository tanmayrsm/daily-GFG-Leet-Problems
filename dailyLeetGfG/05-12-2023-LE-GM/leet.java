class Solution {
    public int numberOfMatches(int n) {
        int ct = 0;
        while(n > 1) {
            ct += n / 2;
            if(n % 2 == 1) {
                n = 1 + (n - 1) / 2;
            } else n /= 2;
        }
        return ct;
    }
}