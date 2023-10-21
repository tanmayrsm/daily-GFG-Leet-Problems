class Solution {    // referred
    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            nums[i] += dq.size() == 0 ? 0 : nums[dq.getFirst()];

            while(dq.size() != 0 && (i - dq.getFirst() >= k || nums[i] >= nums[dq.getLast()])) {
                if(nums[i] >= nums[dq.getLast()])
                    dq.removeLast();
                else
                    dq.removeFirst();
            }

            if(nums[i] > 0)
                dq.addLast(i);
        }

        int maxi = Integer.MIN_VALUE;
        for(int n: nums)
            maxi = Math.max(maxi, n);
        
        return maxi;
    }
}