class Solution {
    public int getLucky(String s, int k) {
        int n = s.length();
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append((int)(s.charAt(i) - 'a') + 1 + "");
        while (k > 0) {
            int cSum = 0;
            int m = sb.length();
            for (int j = 0; j < m; j++) 
                cSum += Integer.parseInt(sb.charAt(j) + "");
            sb = new StringBuilder(String.valueOf(cSum));
            ans = cSum;
            k--;
        }
        return ans;
    }
}