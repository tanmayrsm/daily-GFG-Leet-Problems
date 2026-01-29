class Solution
{
    private static int[][] d = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    public int[][] chefAndWells(int n,int m,char c[][]) {
        int[][] ans = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> pq = new LinkedList<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(c[i][j] == 'N' || c[i][j] == 'W')    // consider '.' as another house, and in final step, make '.' as 0
                    ans[i][j] = 0;
                else    ans[i][j] = -1;
                if(c[i][j] == 'W')
                    pq.add(new int[]{i, j});
            }
        
        while(!pq.isEmpty()) {
            int N = pq.size();
            while(N-- > 0) {
                int[] currPos = pq.poll();
                for(int i = 0; i < d.length; i++) {
                    int newX = d[i][0] + currPos[0];
                    int newY = d[i][1] + currPos[1];
                    if(isValid(n, m, newX, newY, ans) && !vis[newX][newY]) {
                        vis[newX][newY] = true;
                        pq.add(new int[]{newX, newY});
                        ans[newX][newY] = Math.min((ans[newX][newY] == -1 ? Integer.MAX_VALUE : ans[newX][newY]), 
                                            ans[currPos[0]][currPos[1]] + 1);
                    }
                }
                
            }
        }
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(c[i][j] == '.')
                    ans[i][j] = 0;
                else if(ans[i][j] > 0)
                    ans[i][j] *= 2;
                
        return ans;
    }
    private static boolean isValid(int n, int m, int newX, int newY, int[][] ans) {
        if(newX < 0 || newX >= n || newY < 0 || newY >= m || ans[newX][newY] == 0)
            return false;
        return true;
    }
}