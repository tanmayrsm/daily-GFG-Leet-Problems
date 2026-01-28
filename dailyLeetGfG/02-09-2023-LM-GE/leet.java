class Solution {
    private static int n;
    private static int[] dp;
    public int minExtraChar(String s, String[] dict) {
        Set<String> st = new HashSet<>();
        n = s.length();
        dp = new int[n];
        Arrays.fill(dp, -1);
        for(String g:  dict)
            st.add(g);
        
        int p = solve(s, 0, st);
        for(int x : dp)
            System.out.print(x + " :: ");
        return p;
    }

    private static int solve(String s, int idx, Set<String> st) {
        if(idx >= n)   return 0;
        if(dp[idx] != -1)   return dp[idx];

        int ct = n;
        StringBuilder sb = new StringBuilder("");
        for(int i = idx; i < n; i++) {
            sb.append(String.valueOf(s.charAt(i)));
            int count = (st.contains(sb.toString()) ? 0 : sb.length()) + solve(s, i + 1, st);
            ct = Math.min(ct, count);
        }
        return dp[idx] = ct;

    }

    // private static int solve(TreeMap<Integer, Integer> arr, Integer lastKey, Integer currKey) {
    //     if(currKey == null) return arr.get(lastKey) != null ? (arr.get(lastKey) - lastKey + 1) : 0;
    //     int take = 0, nottake = 0;
    //     if(arr.get(lastKey) == null || arr.get(lastKey) < currKey) {
    //         int payload = arr.get(lastKey) == null ? 0 : (arr.get(lastKey) - lastKey + 1);
    //         take = payload + solve(arr, currKey, arr.higherKey(currKey));
    //     }
    //     nottake = 0 + solve(arr, lastKey, arr.higherKey(currKey));
    //     return Math.max(take, nottake);
    // }
}