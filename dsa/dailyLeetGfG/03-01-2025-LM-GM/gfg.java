class Solution {
    public long subarrayXor(int arr[], int k) {
        // code here
        long ans = 0;
        int n = arr.length;
        int pref[] = new int[n];
        // pref[0] = arr[0];
        Map<Integer, Integer> mp = new HashMap<>();
        // mp.put(pref[0], 1);
        for(int i = 0; i < n; i++) {
            if (i > 0)
                pref[i] = arr[i] ^ pref[i - 1];
            else pref[i] = arr[i];
            
            int get = pref[i] ^ k;
            if (mp.containsKey(get))
                ans += mp.get(get);
            if (pref[i] == k)
                ans++;
            mp.put(pref[i], mp.getOrDefault(pref[i], 0) + 1);
        }
        return ans;
    }
}