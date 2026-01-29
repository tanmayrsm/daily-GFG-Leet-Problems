

class Solution
{
    private static int[] dx = new int[] {1, -1, 0 , 0};
	private static int[] dy = new int[] {0, 0, 1, -1};
	
    //Function to find minimum time required to rot all oranges. 
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int N = grid.length;
        int M = grid[0].length;

		boolean[][] vis = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        int noOfFreshOranges = 0;
		for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                vis[i][j] = false;
                if(grid[i][j] == 2)
                    q.add(new int[]{i, j});
                if(grid[i][j] == 1)
                    noOfFreshOranges++;
            }
        }

		int ans = 0;
		while(!q.isEmpty()) {
			int n = q.size();
			boolean hasChange = false;
			while(n > 0) {
				int[] pos = q.poll();
				int X = pos[0];
				int Y = pos[1];
				for(int i = 0; i < 4; i++) {	// move in all 4 dir
                    int changeInX = X + dx[i];
                    int changeInY = Y + dy[i];
					if(changeInX >= 0 && changeInX < N && changeInY >= 0 && changeInY < M && !vis[changeInX][changeInY] && grid[changeInX][changeInY] == 1) {
						vis[changeInX][changeInY] = true;
                        grid[changeInX][changeInY] = 2; // rot this fresh orange
                        noOfFreshOranges--;
						q.offer(new int[] {changeInX, changeInY});
						hasChange = true;
					}
				}
				n--;
			}
			if(hasChange)
			    ans++;
		}
		return noOfFreshOranges != 0 ? -1 : ans;
    }
}