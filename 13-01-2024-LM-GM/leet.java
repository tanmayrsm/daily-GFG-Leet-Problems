class Solution {
    public int minSteps(String s, String t) {
        int n = s.length(), m = t.length(), ans = 0;
        int[] sCt = new int[26];
        int[] tCt = new int[26];
        for(int i = 0; i < n; i++)
            sCt[s.charAt(i) - 'a']++;
        for(int i = 0; i < m; i++)
            tCt[t.charAt(i) - 'a']++;

        for(int i = 0; i < 26; i++)
            if(sCt[i] > tCt[i])
                ans += Math.abs(sCt[i] - tCt[i]);
            
        return ans;
    }
}