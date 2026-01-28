class Solution {
    public int findMaxFish(int[][] grid) {
        int ans = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]!=0){
                    int temp = dfs(grid,i,j);
                    ans = Math.max(ans,temp);
                }
            }
        }
        return ans;
    }
    public int dfs(int[][] grid,int i,int j){
        //visited[i][j] = true;
        int ans = grid[i][j];
        grid[i][j] = 0;
        int[] dirX = {0,0,1,-1};
        int[] dirY = {1,-1,0,0};
        for(int k=0;k<4;k++){
            int newX = dirX[k]+i;
            int newY = dirY[k]+j;
            if(newX>=0 && newX<grid.length && newY>=0 && newY<grid[0].length && grid[newX][newY]!=0){
                ans += dfs(grid,newX,newY);
            }
        }
        return ans;
    }
}