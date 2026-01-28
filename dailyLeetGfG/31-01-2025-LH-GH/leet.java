class Solution {
    class Pair {
        int x, y, id, val;
        Pair(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }
    private static int[][] dir = new int[][] {{0,1}, {0, -1}, {1,0}, {-1, 0}};
    private static boolean[][] vis;
    public int largestIsland(int[][] grid) {
        int n = grid.length, id = 1, ans = 0;
        Pair[][] p = new Pair[n][n];    // also used as vis
        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && p[i][j] == null) {
                    List<Pair> pList = new ArrayList<>();
                    dfs(i, j, grid, id, pList, p);
                    int totalSizeOfIsland = pList.size();
                    ans = Math.max(ans, totalSizeOfIsland);
                    for (Pair pair : pList)
                        pair.val = totalSizeOfIsland;
                    id++;
                }
            }
        for(int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Map<Integer, Pair> mp = new HashMap<>();
                    if (i - 1 >= 0 && p[i - 1][j] != null) {
                        int key = p[i - 1][j].id; 
                        mp.put(key, p[i - 1][j]);
                    }
                    if (j - 1 >= 0 && p[i][j - 1] != null) {
                        int key = p[i][j - 1].id; 
                        mp.put(key, p[i][j - 1]);
                    }
                    if (j + 1 < n && p[i][j + 1] != null) {
                        int key = p[i][j + 1].id; 
                        mp.put(key, p[i][j + 1]);
                    }
                    if (i + 1 < n && p[i + 1][j] != null) {
                        int key = p[i + 1][j].id; 
                        mp.put(key, p[i + 1][j]);
                    }
                    int togetherIslands = 1;
                    for(Map.Entry<Integer, Pair> ex : mp.entrySet())
                        togetherIslands += ex.getValue().val;
                    ans = Math.max(ans, togetherIslands);
                }
            }
            return ans;

    }
    private void dfs(int x, int y, int[][] grid, int id, List<Pair> pList, Pair[][] p) {
        Pair newP = new Pair(x, y, id); 
        pList.add(newP);
        p[x][y] = newP;
        for(int[] d : dir) {
            int xx = d[0] + x, yy = d[1] + y;
            if (xx >= 0 && yy >= 0 && xx < grid.length && yy < grid.length && p[xx][yy] == null && grid[xx][yy] == 1)
                dfs(xx, yy, grid, id, pList, p);
        }
    }
}