class Solution {
    int n, m, MAX = Integer.MAX_VALUE;
    public int minDeletionSize(String[] strs) {
        n = strs.length, m = strs[0].length();
        return solve(0, -1, strs);
    }
    private int solve(int curr, int prev, String[] strs) {
        if (curr == m)  return 0;
        int delete = MAX, notdelete = MAX;
        int res = solve(curr + 1, prev, strs);
        if (res != MAX)
            delete = 1 + res   // delete curr index
        boolean canStay = true;
        if (prev != -1) {
            for (int i = 0; i < n; i++) {
                if (strs[i].charAt(curr) < strs[i].charAt(prev)) {
                    canStay = false;
                    break;
                }
            }
        }
        if (canStay)
            notdelete = solve(curr + 1, curr, strs);
        return Math.min(delete, notdelete);
    }
}


