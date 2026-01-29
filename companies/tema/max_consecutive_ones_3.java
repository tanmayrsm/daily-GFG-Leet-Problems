class Solution {
    public int longestOnes(int[] nums, int k) {
        // sliding window
        // first grab k 0s check how many 1s can be added, then move one zero to right and recal
        int ans = 0, curr = 0, l = 0, r = -1, n = nums.length, K = k;

        // grab k 0s
        for (int i = 0; i < n && k > 0; i++) {
            if (nums[i] == 0) {
                // if(l == -1) l = i;
                r = i;
                k--;
            }
            curr += nums[i];
        }
        if(k > 0)   return nums.length;
        ans = curr + K;
        // System.out.println(l + ":start:" + r + "::" + ans);
        while(r < n) {
            // move r to next 0
            r++;
            while(r < n && nums[r] != 0) {
                curr += nums[r];
                ans = Math.max(ans, curr + K);
                r++;
            }
            // move l to remove a 0
            if (l < r && nums[l] != 0) {
                while(l < r && nums[l] != 0) {
                    curr -= nums[l];
                    l++;
                }
                l++;
            } else {
                l++;
            }
            // System.out.println(l + "::" + r + "::" + curr + ":::" + ans);
            ans = Math.max(ans, curr + K);
        }
        return ans;
    }
}

// [1,1,1,0,0,0,1,1,1,1,0]
// 1 2 3 0 0 0 1 2 3 4 0
// 3 0 0 0 4 0

// 0 0 2 0 0 3 0 2 0 0 0 4, k = 3
// -2 2 -2 3 -1 2 -3 4

