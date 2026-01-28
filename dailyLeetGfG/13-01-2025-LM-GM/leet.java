class Solution {
    public int minimumLength(String s) {
        int[] ct = new int[26];
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;
        for(int x : ct) {
            if (x > 0 && x % 2 == 0)
                ans += 2;
            if (x > 0 && x % 2 == 1)
                ans += 1;
        }
        return ans;
    }
}