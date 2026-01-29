class Solution {
    public String reorganizeString(String s) {
        int n = s.length(), last = 0;
        int[] ct = new int[26];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            ct[s.charAt(i) - 'a']++;

        while(last < n) {
            int last = sb.isEmpty() ? -1 : (sb.getCharAt(last - 1) - 'a');
            int idxWithMaxExcept = getMaxExcept(last, ct);
            if(idxWithMaxExcept == -1)  return "";
            ct[idxWithMaxExcept]--;
            char next = (char)('a' + idxWithMaxExcept);
            sb.append("" + next);
            last++;
        }
        return sb.toString();
    }
    private getMaxExcept(int idx, int[] ct) {
        int max = -1, maxIdx = -1;
        for(int i = 0; i < ct.length; i++) {
            if(i != idx && ct[i] > max) {
                max = ct[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}