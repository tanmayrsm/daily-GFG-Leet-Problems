class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int mod = nums[i] % value;
            if (mod < 0)    mod += value;
            // System.out.println(mod + "::" + nums[i]);
            if (!mp.containsKey(mod)) {
                mp.put(mod, 1);
                nums[i] = mod;
            } else {
                nums[i] = mp.get(mod) * value + mod;
                mp.put(mod, mp.get(mod) + 1);
            }
        }
        Arrays.sort(nums);
        // System.out.println(mp);
        if (nums[0] != 0)   return 0;
        for (int i = 1; i < n; i++) {
            // System.out.println(nums[i - 1] + "::" + nums[i]);
            if (nums[i] - nums[i - 1] != 1) return nums[i - 1] + 1;
        }
        return  nums[n - 1] + 1;
    }
}