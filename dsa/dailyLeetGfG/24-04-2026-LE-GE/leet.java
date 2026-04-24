class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int n = moves.length(), lct = 0, rct = 0, anydir = 0;
        for(int i = 0; i < n; i++) {
            char curr = moves.charAt(i);
            if(curr == 'L') lct++;
            else if (curr == 'R') rct++;
            else anydir++;
        }
        return Math.abs(lct - rct) + anydir;
    }
}