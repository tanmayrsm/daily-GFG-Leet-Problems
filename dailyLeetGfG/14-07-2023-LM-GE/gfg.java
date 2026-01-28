class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        // int[] dp = new int[n];
        int ans = 0;
        for(int i = n - 1; i >= 0; i--) {
            int searchFor = arr[i] + difference;    // if curr element is 3, and diff = 1, search for 4 in map as [3,4] forms a subsequence with diff = 1
            if(map.get(searchFor) != null) {
                map.put(arr[i], map.get(searchFor) + 1);
            } else
                map.put(arr[i], 1);
            ans = Math.max(ans, map.get(arr[i]));
        }
        return ans;
    }
}