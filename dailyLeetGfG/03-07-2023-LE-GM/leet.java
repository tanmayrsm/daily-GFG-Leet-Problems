class Solution {
    public boolean buddyStrings(String s, String goal) {
        if(goal.length() != s.length())
            return false;
        char[] sArr    = s.toCharArray();
        char[] goalArr = goal.toCharArray();
        
        // both strings same
        if(s.equals(goal)) {
            int[] cArr = new int[26];
            for(char c : sArr) {
                if(cArr[c - 'a'] > 0)      // any repeating character found, we can swap
                    return true;
                cArr[c - 'a']++;
            }
            return false;
        }
        
        int mismatch = 0;
        int lastMis = -1;
        for(int i = 0; i < sArr.length; i++) {
            if(sArr[i] != goalArr[i]) {
                mismatch++;
                if(mismatch > 2)
                    return false;
                if(lastMis != -1) {
                    if(sArr[i] == goalArr[lastMis] && sArr[lastMis] == goalArr[i]) {

                    } else return false;
                }
                lastMis = i;
            }
        }

        return mismatch == 1 ? false : true;
    }
}