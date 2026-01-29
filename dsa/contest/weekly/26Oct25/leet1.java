class Solution {
    public long removeZeros(long n) {
        long ans = 0, multiplier = 1;
        while(n > 0) {
            long no = n % 10;
            if(no != 0) {
                ans += multiplier * no;
                multiplier *= 10;
            }
            n /= 10;
        }
        return ans;
    }
}