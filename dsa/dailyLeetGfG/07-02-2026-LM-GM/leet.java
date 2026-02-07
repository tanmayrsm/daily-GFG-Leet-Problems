class Solution {
    public int minimumDeletions(String s) {
        int n = s.length(), l = 0, r = n - 1, altB = 0, altA = 0, ans = 0;
        while(l < n && s.charAt(l) == 'a')   l++;
        while(r >= 0 && s.charAt(r) == 'b')   r--;
        for(int i = l; i <=r; i++) {
            char curr = s.charAt(i);
            if(curr == 'b') {
                altB++;
            } else {
                // a encountered
                ans = Math.min(altB, ans + 1);
            }
        }
        return ans;
    }
}