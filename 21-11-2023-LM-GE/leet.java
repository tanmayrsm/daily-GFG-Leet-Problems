class Solution {
    static int mod = 1000000007;
    public int countNicePairs(int[] nums) {
        // nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        // nums[i] - rev(nums[i]) == nums[j] - rev(nums[j])
        Map<Integer, Integer> mp = new HashMap<>();
        long ans = 0;
        for(int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] - rev(nums[i]);
            if(!mp.containsKey(nums[i]))
                mp.put(nums[i], 1);
            else    mp.put(nums[i], mp.get(nums[i]) + 1);
        }

        for(Map.Entry<Integer, Integer> m : mp.entrySet()) {
            long val = m.getValue();
            long noOfPairs = (val * (val - 1)) / 2;
            ans += noOfPairs % mod;
            ans %= mod;
        }
        return (int)ans;
    }
    private static int rev(int a) {
        int rev = 0;
        while(a > 0) {
            rev = rev*10 + a % 10;
            a = a / 10;
        }
        return rev;
    }
}