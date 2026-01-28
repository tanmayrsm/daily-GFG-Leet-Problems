import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// mark all islands in g1 -> as some constant no representing island no
// same for grid2, but in g2, when u traverse list of islands, that same pos in grid1, must have same constant no thruout


class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int k = 2, n = grid1.length, m = grid1[0].length, ans = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid1[i][j] == 1) {
                    markIsland(grid1, i, j, k);
                    k++;
                }
                if (grid2[i][j] == 1) {
                    markIsland(grid2, i, j, k);
                    k++;
                }

                // previously had this in another for(i)..for(j) loop for understanding, but optmising now
                if (grid2[i][j] != 0) {
                    int islandKey = grid2[i][j];
                    if (mp.containsKey(islandKey) && mp.get(islandKey) != -1 && grid1[i][j] != mp.get(islandKey)) {
                        mp.put(islandKey, -1);
                        ans--;
                    } else if (!mp.containsKey(islandKey)) {
                        if (grid1[i][j] == 0)   mp.put(islandKey, -1);
                        else {
                            mp.put(islandKey, grid1[i][j]);
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;
    }

    private static void markIsland(int[][] grid, int x, int y, int k) {
        Queue<int[]> q = new LinkedList<>();
        int[][] dir = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        q.offer(new int[] {x, y});
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int xx = pos[0], yy = pos[1];
            if (grid[xx][yy] == 1) {
                grid[xx][yy] = k;
                for (int[] d : dir) {
                    int newX = xx + d[0], newY = yy + d[1];
                    if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == 1)
                        q.offer(new int[] {newX, newY});
                } 
            }
        }
    }
}


