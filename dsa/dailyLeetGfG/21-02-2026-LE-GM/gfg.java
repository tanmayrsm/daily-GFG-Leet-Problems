class Solution {
    public int hIndex(int[] citations) {
        // code here
        int ans = -1, n = citations.length;
        Arrays.sort(citations);
        if (citations[0] > n) ans = n;
        for (int i = 0; i < n; i++) {
            int rem = n - i;
            if (rem >= citations[i])    ans = citations[i];
            else if (citations[i] > rem) {
                ans = Math.max(ans, rem);
            }
        }
        return ans;
    }
}