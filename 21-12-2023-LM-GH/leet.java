class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int ans = 0;
        Arrays.sort(points, (point1, point2) -> {
            if(point1[0] < point2[0])   return -1;
            if(point1[0] > point2[0])   return 1;
            return 0;
        });
        for(int i = 1; i < points.length; i++) {
            ans = Math.max(ans, points[i][0] - points[i-1][0]);
        }
        return ans;
    }
}