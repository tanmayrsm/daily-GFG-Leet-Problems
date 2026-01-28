class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, String> mp = new TreeMap<>();
        int n = names.length,  k = n - 1;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++)
            mp.put(heights[i], names[i]);
        for (Map.Entry<Integer, String> e : mp.entrySet()) {
            ans[k--] = e.getValue();
        }
        return ans;
    }
}