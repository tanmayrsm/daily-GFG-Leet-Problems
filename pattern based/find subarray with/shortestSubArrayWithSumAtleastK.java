import java.util.HashMap;
import java.util.Map;

class shortestSubArrayWithSumAtleastK {
    public int shortestSubarray(int[] nums, int k) {
        int ans = Integer.MAX_VALUE, n = nums.length;
        Map<Integer, Integer> store = new HashMap<>();
        store.put(0, -1);

        for(int i = 0; i < n; i++) {
            if(nums[i] >= k)    return 1;
            if(i > 0)
                nums[i] += nums[i - 1];
            int need = nums[i] - k;
            if(store.containsKey(need))
                ans = Math.min(ans, i - store.get(need));
            store.put(nums[i], i);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;   
    }
}

// -1 -22 -33 48 99 37  4   -31
// k = 140

// -1 -23 -56 -8 91 128 132 101
// 
