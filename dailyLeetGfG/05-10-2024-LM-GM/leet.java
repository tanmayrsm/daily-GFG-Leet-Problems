class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // check if s2 contains s1
        int n = s1.length(), m = s2.length(), l = 0, r = n - 1;
        if(n > m)   return false;

        int[] sOne = new int[26];
        int[] sTwo = new int[26];

        for(int i = 0; i < n; i++)
            sOne[s1.charAt(i) - 'a']++;
        for(int i = 0; i < n; i++)
            sTwo[s2.charAt(i) - 'a']++;
        
        
        while (r < m) {
            if(isValid(sOne, sTwo)) 
                return true;
            if (r + 1 < m) {
                sTwo[s2.charAt(l++) - 'a']--;
                sTwo[s2.charAt(++r) - 'a']++;
            } else break;
        }

        return false;
    }

    private boolean isValid(int[] sOne, int[] sTwo) {
        for(int i = 0; i < sOne.length; i++) {
            if((sOne[i] > 0 && sTwo[i] != sOne[i]) || (sOne[i] == 0 && sTwo[i] > 0))
                return false;
        }
        return true;
    }
}


// Input: s1 = "ab", s2 = "eidboaoo"
// Output: false


