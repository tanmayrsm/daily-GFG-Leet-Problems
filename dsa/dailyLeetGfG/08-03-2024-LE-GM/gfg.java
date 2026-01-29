
//User function Template for Java

class Solution {
    boolean sameFreq(String s) {
        // code here
        int m = 26;
        int[] freq = new int[m];
        int n = s.length();
        for(int i = 0; i < n; i++)
            freq[s.charAt(i) - 'a']++;
        Arrays.sort(freq);
        for(int i = freq.length - 1; i > 0; i--) {
            if(freq[i] == 0)
                break;
            if(freq[i - 1] != 0 && freq[i] != freq[i - 1]) {
                if(i == m - 1 && freq[i] - 1 == freq[i - 1])
                    continue;
                return false;
            }
        }
        return true;
    }
}