import java.util.HashMap;
import java.util.Map;

class shortestSubArrayWithSumAtleastK {
    public int shortestSubarray(int[] nums, int k) {
        int ans = Integer.MAX_VALUE, n = nums.length;
        Map<Integer, Integer> store = new HashMap<>();
        store.put(0, -1);

        for(int i = 0; i < n; i++) {
            if(nums[i] == k)    return 1;
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

// -1 9 8 -7 2  3  4  -5  5
// k = 5

// -1 8 16 9 11 14 18 13 18 --> prefix sum
// -1 : 0, check if 5 - (-1) in map ? ans = Math.min(ans,index of 6 - currIndex) : proceed
// {-1 : 0, 8: 1}, check if 5 - 8 in map
// 

// 2 -1 2, k = 3, {0 : -1}
// 2  1 3 --> prefix array
// ...2-3 = -1 in map ? no {0 : -1, 2 : 0}
// ...1-3 = -2 in map ? no {0 : -1, 2 : 0, -2 : 1}
// ...3-3 = 0 in map ? yes {0 : 2 , 2 : 0,  -2 : 1} ans = Math.min(INFINITE, 2 - (-1))
    // -2 4 -3 6
    // -2 2 -1 5
    // -2 2  1 3



// -1 9 8 -7 2  3  4  -5  5
// k = 7
// -1 8 16 9 11 14 18 13 18 --> prefix sum