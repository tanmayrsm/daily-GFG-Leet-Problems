class Solution {
    public int minimumPushes(String word) {
        int[] ch = new int[26];
        int n = word.length(), ans = 0, k = 1, usedNoOfTimes = 1;
        for (int i = 0; i < n; i++) 
            ch[word.charAt(i) - 'a']++;
        Arrays.sort(ch);
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] > 0) {
                ans += ch[i] * k;
                if (usedNoOfTimes == 8) {   // 8 btns max
                    usedNoOfTimes = 1;
                    k++;    // click counter increased
                } else
                   usedNoOfTimes++;
            }
        }
        return ans;

    }
}

