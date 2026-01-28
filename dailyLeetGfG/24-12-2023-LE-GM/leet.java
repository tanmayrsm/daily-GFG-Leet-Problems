class Solution {
    // after seeing hint, its clear
    // final string would be either - 101010 OR 010101
    // hence find min of both, if resultant were stsrt with 0 or 1
    public int minOperations(String s) {
        int n = s.length();
        boolean firstPref = true, secondPref = false;   // firstPref = 101010, secondPref 010101
        int firstPrefCollision = 0, secondPrefCollision = 0;
        for(int i = 0; i < n; i++) {
            char curr = s.charAt(i);
            if(curr == '0') {
                if(firstPref)   firstPrefCollision++;
                if(secondPref)   secondPrefCollision++;
            } else {
                if(!firstPref)   firstPrefCollision++;
                if(!secondPref)   secondPrefCollision++;
            }
            firstPref = !firstPref;
            secondPref = !secondPref;
        }
        return Math.min(firstPrefCollision, secondPrefCollision);
    }
}