class Solution {
    int countStrings(String s) {
        // code here
        int n = s.length(), ans = 0;
        int[] ct = new int[26];
        boolean duplicate = false;
        for (int i = n - 1; i >= 0; i--) {
            char curr = s.charAt(i);
            int idx = curr - 'a', uniqueCt = 0;
            for (int j = 0; j < ct.length; j++) {
                if (j != idx) {
                    uniqueCt += ct[j];
                } else if (j == idx && ct[j] > 0) {
                    duplicate = true;
                }
            }
            ans += uniqueCt;
            ct[idx]++;
        }
        if (duplicate)  ans++;
        return ans;
    }
}