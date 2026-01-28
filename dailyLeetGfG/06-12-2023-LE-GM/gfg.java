
class Solution {
    int countX(int L, int R, int X) {
        // code here
        int ans = 0;
        String x = String.valueOf(X);
        for(int i = L + 1; i < R; i++) {
            String j = String.valueOf(i);
            int p = j.length() - j.replace(x, "").length();
            ans += p;
        }
        return ans;
    }
};