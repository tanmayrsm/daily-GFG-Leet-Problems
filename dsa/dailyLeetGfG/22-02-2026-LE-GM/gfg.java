class Solution {
    public long subarrayXor(int arr[], int k) {
        // code here
        long ans = 0; int res = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for(int x : arr) {
            res ^= x;
            if(mp.containsKey(res ^ k))
                ans += mp.get(res ^ k);
            mp.put(res, mp.getOrDefault(res, 0) + 1);
        }
        return ans;
    }
}