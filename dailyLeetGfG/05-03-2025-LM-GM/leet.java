class Solution {
    public long coloredCells(int n) {
        long ans = 1, diff = 0;
        for (int i = 1; i < n; i++) {
            diff += 4;
            ans += diff;
        }
        return ans;
    }
}
// observed pattern -
// 1,5,13,25,41,61,85
// 0,4, 8,12,16,20,24

