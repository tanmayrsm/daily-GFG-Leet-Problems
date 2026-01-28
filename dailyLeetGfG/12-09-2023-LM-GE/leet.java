class Solution {
    public int minDeletions(String s) {
        // BRUTE force solution - store freq of each char in array
        // for each char, find if similar freq exisits in arr, if yes, reduce curr chars freq, increase counter
        // do it for each character

        int n = s.length();
        int[] freq = new int[26];
        int ct = 0;
        // store freq in arr
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            freq[c - 'a']++;
        }

        // for each char, reduce freq, if needed
        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0) {
                ct += reduceFreq(freq, i);
            }
        }
        return ct;
    }

    private static int reduceFreq(int[] freq, int index) {
        int ct = 0;
        boolean contains = true;

        while(contains) {
            contains = false;
            for(int i = 0; i < freq.length; i++) {
                if(i != index && freq[i] == freq[index] && freq[index] != 0) {  // condition to check if another char exists with same freq
                    contains = true;
                    ct++;
                }
                if(freq[index] == 0)    return ct;
                if(contains) {
                    freq[index]--;          // if yes, then reduce curr freq
                    break;
                }
            }
        }
        return ct;
    }
}