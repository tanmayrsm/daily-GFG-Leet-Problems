class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length, ans = Integer.MAX_VALUE;
        // for(int i = 0; i < n; i++) {
        //     int rev = getRev(nums[i]);
        //     System.out.println(nums[i] + "::" + rev);
        //     if(mp.containsKey(rev)) {
        //         ans = Math.min(ans, i - mp.get(rev));
        //     }
        //     mp.put(nums[i], i);
        // }
        mp.clear();

        for(int i = n - 1; i >= 0; i--) {
            int rev = getRev(nums[i]);
            // System.out.println(nums[i] + "::" + rev);
            if(mp.containsKey(rev)) {
                ans = Math.min(ans, mp.get(rev) - i);
            }
            mp.put(nums[i], i);
        }

        if(ans == Integer.MAX_VALUE)    return -1;
        return ans;
    }
    private int getRev(int no) {
        int rev = 0;
        while(no > 0) {
            rev *= 10;
            rev += no % 10;
            no /= 10;
        }
        return rev;
    }
}