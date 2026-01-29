import java.util.HashMap;

class Solution {
    public int cntSubarrays(int[] arr, int k) {
        // code here
        int n = arr.length, prev = 0;
        int[] pref = new int[n];
        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < n; i++) {
            pref[i] = ((i - 1 >= 0) ? pref[i - 1] : 0) + arr[i];
            mp.put(pref[i], mp.getOrDefault(pref[i], 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (mp.containsKey(arr[i] - k)) {
                ans += mp.get(arr[i] - k);
            }
        }
        return ans;
    }
}
/*
Input: arr[] = [10, 2, -2, -20, 10], k = -10
Output: 3 [10, 2, -2, -20],[-20, 10],[2, -2, -20, 10]

10 12 10 -10 0
x-k -> 2
k-x -> 1

1 2 3, k = 5
1 3 6

 */
