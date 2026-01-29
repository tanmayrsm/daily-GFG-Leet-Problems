class Solution {
    public int minBitFlips(int start, int goal) {
        int ans = 0;
        while(start > 0 && goal > 0) {
            int s1 = start % 2, s2 = goal % 2;
            if (s1 != s2)
                ans++;
            start /= 2;
            goal /= 2;
        }
        while(goal > 0) {
            if (goal % 2 == 1)  ans++;
            goal /= 2;
        }
        while(start > 0) {
            if (start % 2 == 1)  ans++;
            start /= 2;
        }
        return ans;
    }
}