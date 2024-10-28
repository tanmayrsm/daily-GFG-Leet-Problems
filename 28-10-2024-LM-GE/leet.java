class Solution {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        int ans = -1;
        Map<Integer, Integer> ct = new HashMap<>();
        for(int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int prev = num * num;
            if(ct.containsKey(prev))
                ct.put(num, ct.get(prev) + 1);
            else ct.put(num, 1);
            ans = Math.max(ans, ct.get(num));
        }
        System.out.println(ct);
        return ans == 1 ? -1 : ans;
    }
}