class Solution {
    public int minOperations(int[] nums, int k) {
        int xor = 0, ans = 0;
        for(int x : nums)   xor ^= x;
        String res = Integer.toBinaryString(res);
        String res2 = Integer.toBinaryString(k);
        int n = res.length() - 1, m = res2.length() - 1;

        while(n >= 0 && m >= 0) {
            if(res.charAt(n--) != res2.charAt(m--)) ans++;
        }

        while(n >= 0)
            if(res.charAt(n--) == '1')  ans++;

        while(m >= 0)
            if(res2.charAt(m--) == '1')  ans++;
        
        return ans;
    }
}