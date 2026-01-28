class Solution {
    public int largestAltitude(int[] gain) {
        int highest = 0;
        int currAlt = 0;
        for(int x : gain) {
            currAlt += x;
            highest = Math.max(highest, currAlt);
        }
        return highest;
    }
}