class Solution {
    public int[][] intervalIntersection(int[][] f, int[][] s) {
        List<int[]> ans = new ArrayList<>();
        int l = 0, r = 0, n = f.length, m = s.length;
        while(l < n && r < m) {
            int[] intersection = intersect(f[l], s[r]);
            if (intersection[0] != -1) ans.add(intersection);
            if (f[l][1] >= s[r][1]) r++;
            else l++;
        }
        return ans.toArray(new int[0][]);
    }
    private int[] intersect(int[] a, int[] b) {
        if (a[0] > b[1] || b[0] > a[1]) return new int[] {-1, -1};
        int left = Math.max(a[0], b[0]);
        int right = Math.min(a[1], b[1]);
        return new int[] {left, right};
    }
}