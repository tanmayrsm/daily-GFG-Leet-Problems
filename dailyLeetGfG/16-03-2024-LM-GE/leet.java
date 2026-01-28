class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int ans = 0;
        mp.put(0, -1);
        
        // treat 0s as -1, and find prefixSum
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0)    nums[i] = -1;
            if(i - 1 >= 0)
                nums[i] += nums[i - 1];
            if(mp.containsKey(nums[i]))
                ans = Math.max(ans, i - mp.get(nums[i]));
            else mp.put(nums[i], i);
        }
        
        return ans;
    }
}