class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, r = 0;
        while(r < n) {
            if (r + 1 == n) return true;
            if (bits[r] == 1)   r += 2;
            else r++;
        }
        return false;
    }
}