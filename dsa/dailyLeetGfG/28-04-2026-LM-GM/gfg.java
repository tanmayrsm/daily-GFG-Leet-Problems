class Solution {
    public int longestSubstr(String s, int k) {
        // code here
        int maxLen = Integer.MIN_VALUE;
        for(char c='A';c<='Z';c++){
            maxLen = Math.max(maxLen,longestSubstring(s,c,k));
        }
        return maxLen;
    }

    int longestSubstring(String s,char c,int k){
        int diffCount = 0, winStart = 0;
        int maxLen = Integer.MIN_VALUE;

        for(int winEnd=0;winEnd<s.length();winEnd++){
            char curr = s.charAt(winEnd);
            diffCount += (curr==c?0:1);

            while(diffCount>k){
                char leftChar = s.charAt(winStart);
                diffCount -= (leftChar==c?0:1);
                winStart++;
            }
            maxLen = Math.max(maxLen,winEnd-winStart+1);
        }

        return maxLen;
    }
}
