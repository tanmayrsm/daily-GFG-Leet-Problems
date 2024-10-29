import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // simple BFS approach
    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int[][] dir = new int[][] {{-1, 1}, {0, 1}, {1, 1}};
    public int maxMoves(int[][] grid) {
        int n = grid.length, m = grid[0].length, ans = -1;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i < grid.length; i++)
            q.offer(new Pair(i, 0));
        vis[0][0] = true;
        while (!q.isEmpty()) {
            int N = q.size();
            while (N-- > 0) {
                Pair p = q.poll();
                for(int[] d : dir) {
                    int xx = p.x + d[0], yy = p.y + d[1];
                    if(xx >= 0 && yy >= 0 && xx < n && yy < m && !vis[xx][yy] && grid[xx][yy] > grid[p.x][p.y]) {
                        q.offer(new Pair(xx, yy));
                        vis[xx][yy] = true;
                    }
                }
            }
            ans++;
        }
        return ans;
    }
}