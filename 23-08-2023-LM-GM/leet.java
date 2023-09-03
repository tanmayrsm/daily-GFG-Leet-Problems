class Solution {
    static int[] charStore;
    public String reorganizeString(String s) {
        charStore = new int[26];
        char[] sb = new char[s.length()];
        char[] sArr = s.toCharArray();
        for(char c : sArr)
            charStore[c - 'a']++;

        int ind = -1, ct = 0, maxi = 0;
        while(ct < s.length()) {
            maxi = getMax(ind);
            if(maxi == -1)  return "";
            sb[ct++] = (char)(maxi + 'a');
            ind = maxi;
        }
        return new String(sb);
    }

    // method to get character whose frequency is maximum, apart from last character used
    // if no such char exists, return -1
    private static int getMax(int lastIndexUsed) {
        int maxIndex = 0, maxNo = 0;
        for(int i = 0; i < 26; i++)
            if(i != lastIndexUsed && charStore[i] > maxNo) {
                maxNo = charStore[i];
                maxIndex = i;
            }
        if(maxNo == 0)
            return -1;
        charStore[maxIndex]--;
        return maxIndex;
    }
}