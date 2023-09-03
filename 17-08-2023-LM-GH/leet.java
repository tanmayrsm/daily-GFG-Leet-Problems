class Solution {
    static int n, m;
    static int[][] d = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int[][] updateMatrix(int[][] mat) {
        n = mat.length; m = mat[0].length;
        int[][] ans = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        
        for(int[] x : ans)  Arrays.fill(x, -1);

        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(mat[i][j] == 0) {
                    ans[i][j] = 0;
                    for(int[] x : d) {
                        int xx = i + x[0], yy = j + x[1];
                        if(isValid(xx, yy) && mat[xx][yy] == 1 && ans[xx][yy] == -1) 
                            q.offer(new int[] {xx, yy});
                    }
                }
            }

        while(!q.isEmpty()) {
            int n = q.size();
            while(n-- > 0) {
                int[] dem = q.poll();
                int x = dem[0], y = dem[1];
                if(ans[x][y] == -1) {
                    int maxi = Integer.MAX_VALUE;
                    for(int[] k : d) {
                        int xx = x + k[0], yy = y + k[1];
                        maxi = Math.min(maxi, isValid(xx, yy) && ans[xx][yy] != -1 ? ans[xx][yy] : Integer.MAX_VALUE);
                        if(isValid(xx, yy) && ans[xx][yy] == -1 && mat[xx][yy] == 1)
                            q.offer(new int[] {xx, yy});
                    }
                    ans[x][y] = 1 + maxi;
                }
            }
        }
        return ans;
    }

    private static boolean isValid(int x, int y) {
        if(x < 0 || y < 0 || x >= n || y >= m)  return false;
        return true;
    }
}