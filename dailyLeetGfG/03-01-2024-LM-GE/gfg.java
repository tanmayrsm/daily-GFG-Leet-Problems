
class Solution {
    public int smallestSubstring(String s) {
        // Code here
        int curr0 = 0, curr1 = 0, curr2 = 0, ans = Integer.MAX_VALUE, n = s.length();
        int l = 0, r = 0;
        while(r < n) {
            while(r < n && (curr0 == 0 || curr1 == 0 || curr2 == 0)) {
                if(curr0 != 0 && curr1 != 0 && curr2 != 0)
                    ans = Math.min(ans, r - l);
                char c = s.charAt(r);
                if(c == '0')    curr0++;
                if(c == '1')    curr1++;
                if(c == '2')    curr2++;
                r++;
            }

            while(l < r && curr0 != 0 && curr1 != 0 && curr2 != 0 ) {
                if(curr0 != 0 && curr1 != 0 && curr2 != 0)
                    ans = Math.min(ans, r - l);
                char c = s.charAt(l);
                if(c == '0')    curr0--;
                if(c == '1')    curr1--;
                if(c == '2')    curr2--;
                l++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
};