class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        int n = nums.length;
        int last = -1;
        
        for(int i = 0; i < n; i++) {
            int curr = nums[i];
            if (i == 0)
              last = nums[0] % k;
            else {
                last = (nums[i] + last) % k;
            }
            if(last == 0 && i > 0)  return true;
            if(mp.containsKey(last)) {
                if (i - mp.get(last) >= 2)   
                    return true;
            }
            else mp.put(last, i);
        }
        return false;
    }
}

// [23,2,4,6,7], k = 6
// [23, 25, 29, 35, 42]
// [5, 1, 5, 5, 0]