class Solution {
    public int firstMissingPositive(int[] nums) {
        // smallest missing positive no
        int ans = 1;
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], true);
        }
        // for(int i = 0; i < nums.length; i++) {
        //     if(nums[i] > 0 && nums[i] == ans) {
                while(map.get(ans) != null)
                    ans++;
        //     }
        // }
        return ans;
    }
}