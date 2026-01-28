class Solution {
    public int longestKSubstr(String s, int k) {
        // code here
        int n = s.length();
        int[] ct = new int[26];
        int l = 0, r = 0, ans = 0;
        while(r < n) {
            while(r < n && checkIfKUniques(ct, k) < k) {
                ct[s.charAt(r) - 'a']++;
                r++;
            }
            while(checkIfKUniques(ct, k) == k) {
                ans = Math.max(ans, r - l);
                if (r >= n) break;
                ct[s.charAt(r) - 'a']++;
                r++;
            }
            while(l < r && checkIfKUniques(ct, k) > k) {
                ct[s.charAt(l) - 'a']--;
                l++;
            }
        }
        return ans == 0 ? -1 : ans;
    }
    private int checkIfKUniques(int[] ct, int k) {
        int uniques = 0;
        for (int i = 0; i < ct.length; i++) {
            if (ct[i] > 0)  uniques++;
        }
        return uniques;
    }
}