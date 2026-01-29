class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int n = isWater.length, m = isWater[0].length, ans = 0;
        int[][] dir = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (isWater[i][j] == 1) {
                    q.offer(new int[] {i, j});
                    vis[i][j] = true;
                    isWater[i][j] = 0;
                }
            }
        
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0], y = pos[1];
            for(int[] d : dir) {
                int xx = x + d[0], yy = y + d[1];
                if (xx >= 0 && yy >= 0 && xx < n && yy < m && !vis[xx][yy]) {
                    isWater[xx][yy] = isWater[x][y] + 1;
                    vis[xx][yy] = true;
                    q.offer(new int[] {xx, yy});
                    ans = Math.max(ans, isWater[xx][yy]);
                }
            }
        }
        return isWater;
    }
}