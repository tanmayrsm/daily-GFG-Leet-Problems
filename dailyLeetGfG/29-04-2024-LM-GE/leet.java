class Solution {
    public int minOperations(int[] nums, int k) {
        int finXor = 0;
        for(int x : nums)   finXor ^= x;
        char[] binFin = Integer.toBinaryString(finXor).toCharArray();
        char[] kCh = Integer.toBinaryString(k).toCharArray();
        int l = binFin.length - 1, r = kCh.length - 1, ans = 0;
        while(l >= 0 && r >= 0) {
            if(binFin[l] != kCh[r]) ans++;
            l--;
            r--;
        }
        while(l >= 0) {
            if(binFin[l] == '1')    ans++;
            l--;
        }
        while(r >= 0) {
            if(kCh[r] == '1')    ans++;
            r--;
        }
        return ans;
    }
}