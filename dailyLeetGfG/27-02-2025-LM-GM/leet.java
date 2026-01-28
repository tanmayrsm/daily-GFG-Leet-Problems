class Solution {
    private static int n;
    private static Map<Integer, Integer> m;
    public int lenLongestFibSubseq(int[] arr) {
        n = arr.length;
        m = new HashMap<>();
        int ans = 0;
        
        for(int i = 0; i < n; i++)
            m.put(arr[i], i);

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                ans = Math.max(ans, solve(arr, i, j));
            }
        }
        return ans == 0 ? ans : ans + 2;
    }

    private static int solve(int[] arr, int currIndex1, int currIndex2) {
        int nxt = arr[currIndex1] + arr[currIndex2];
        int k = m.getOrDefault(nxt, -1);
        if(k == -1) return 0;
        return 1 + solve(arr, currIndex2, k);
    }
}