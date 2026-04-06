class Solution {
    public int minLengthAfterRemovals(String s) {
        int n = s.length(), aCt = 0, bCt = 0;
        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'a')  aCt++;
            else bCt++;
        }
        return Math.abs(aCt - bCt);
    }
}