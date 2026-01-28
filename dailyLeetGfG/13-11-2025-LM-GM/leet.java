class Solution {
    public int maxOperations(String s) {
        int n = s.length(), ans = 0, r = 0;
        int setOfOnes = 0;
        while(r < n) {
            if (s.charAt(r) == '1') {
                while (r < n && s.charAt(r) == '1') {
                    r++;
                    setOfOnes++;
                }
                if (r < n && s.charAt(r) == '0') {
                    // System.out.println("adde ::" + setOfOnes);
                    ans += setOfOnes;
                }
            } else r++;
        }
        return ans;
    }
}

// 110010001

// last set of consecutive 1s -> 2 then u get 0 and 1 (or n)
// ans += 2
// consec1 += prev + newer;
// ans += 3


