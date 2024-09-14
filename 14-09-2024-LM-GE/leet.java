import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int longestSubarray(int[] nums) {
        int maxNo = -1, maxIndex = -1, n = nums.length, ans = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] > maxNo) {
                maxNo = nums[i];
                maxIndex = i;
            }
        
        // find longest subarray for maxNo present in array
        while(r < n) {
            while (nums[r] != maxNo)
                r++;
            int ct = 0;
            while(nums[r] == maxNo) {
                ct++;
                r++;
            }
            ans = Math.max(ans, ct);
        }
        return ans;
    }
}