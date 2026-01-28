class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // can also try two pointer approach to reduce space here

        Queue<Integer> q = new LinkedList<>();
        int n = nums.length, ans = 0;
        long currPro = 1;

        for(int i = 0; i < n; i++) {
            if(!q.isEmpty()) {
                if(currPro * nums[i] < k) {
                    q.offer(nums[i]);
                } else {
                    while(!q.isEmpty() && currPro * nums[i] >= k) {
                        currPro /= q.poll();
                    }
                    if(currPro * nums[i] < k) {
                        q.offer(nums[i]);
                    }
                }
            } else if (nums[i] < k) {
                q.offer(nums[i]);
            }

            if(nums[i] < k) {
                ans += q.size();
                currPro *= nums[i];
            }
        }
        return ans;
    }
}