class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        // -1 1 0 1 0 1
        //  0 1 1 2 2 3

        // sum - goal exists
        // 1 1 2, 1 1 2
        // curr - x = goal
        // x = curr - goal
        // find how many x
        int n = nums.length, ans = 0;
        if(goal == 0) {
            int ct = 0, i = 0;
            while(i < n) {
                ct = 0;
                while(i < n && nums[i] == 0) {
                    ct++;
                    i++;
                }
                i++;
                ans += ct * (ct + 1) / 2;
            }
            return ans;
        }
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for(int i = 0; i < n; i++) {
            if(i - 1 >= 0)
                nums[i] += nums[i - 1];
            mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
            int search = nums[i] - goal;
            if(mp.containsKey(search))
                ans += mp.get(search);
        }
        // System.out.println(mp);
        return ans;

    }
}