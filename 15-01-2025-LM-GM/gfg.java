class Solution {
    public int longestSubarray(int[] arr, int k) {
        // code here
        Map<Integer, Integer> mp = new HashMap<>();
        int curr = 0, ans = 0;
        mp.put(0, -1);
        for(int i = 0; i < arr.length; i++) {
            curr += arr[i];
            int lookFor = curr - k;
            if (mp.containsKey(lookFor))
                ans = Math.max(ans, i - mp.get(lookFor));
            if (!mp.containsKey(curr))
                mp.put(curr, i);
        }
        return ans;
    }
}