class Solution {
    public long countSubarrays(int[] A, int minK, int maxK) {    // reff
        // all nos, who are out of range, can be called as 'bad_i'

        long res = 0, bad_i = -1, jmin = -1, jmax = -1, n = A.length;
        for (int i = 0; i < n; ++i) {
            if (A[i] < minK || A[i] > maxK) bad_i = i;
            if (A[i] == minK) jmin = i;
            if (A[i] == maxK) jmax = i;
            res += Math.max(0L, Math.min(jmin, jmax) - bad_i);  // really imp observation
        }
        return res;
    }
}