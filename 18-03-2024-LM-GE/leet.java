class Solution {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        int[] curr = points[0];
        int ans = 1;
        for(int i = 1; i < points.length; i++) {
            if(points[i][0] <= curr[1]) {   // colliding point with previous
                curr = new int[] {Math.max(curr[0], points[i][0]), Math.min(curr[1], points[i][1])};
            } else {
                ans++;
                curr = points[i];
            }
        }
        return ans;
    }
}