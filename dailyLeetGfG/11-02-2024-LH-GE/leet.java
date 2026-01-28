class Solution {
    private static int rows, cols;
    private static Map<String, Integer> dp;
    public int cherryPickup(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        dp = new HashMap<>();
        return solve(0, 0, 0, cols - 1, grid);
    }
    private static int solve(int x1, int y1, int x2, int y2, int[][] grid) {
        StringBuilder key = new StringBuilder();
        key.append(String.valueOf(x1)).append("$").append(String.valueOf(y1)).append(String.valueOf(x2)).append("$").append(String.valueOf(y2));
        String ks = key.toString();
        if(dp.containsKey(ks))  return dp.get(ks);

        int d1 = 0, d2 = 0, d3 = 0;
        int t1 = grid[x1][y1];
        grid[x1][y1] = 0;

        int t2 = grid[x2][y2];
        grid[x2][y2] = 0;
        if(isValid(x1 + 1, y1 - 1)) {
            int a = 0,b = 0,c = 0;
            // 3 moves for robot 2
            if(isValid(x2 + 1, y2 - 1)) {
                a = solve(x1 + 1, y1 - 1, x2 + 1, y2 - 1, grid);
            }
            if(isValid(x2 + 1, y2)) {
                b = solve(x1 + 1, y1 - 1, x2 + 1, y2, grid);
            }
            if(isValid(x2 + 1, y2 + 1)) {
                c = solve(x1 + 1, y1 - 1, x2 + 1, y2 + 1, grid);
            }
            d1 = Math.max(a, Math.max(b, c));
        }
        if(isValid(x1 + 1, y1)) {
            int a = 0,b = 0,c = 0;
            // 3 moves for robot 2
            if(isValid(x2 + 1, y2 - 1)) {
                a = solve(x1 + 1, y1, x2 + 1, y2 - 1, grid);
            }
            if(isValid(x2 + 1, y2)) {
                b = solve(x1 + 1, y1, x2 + 1, y2, grid);
            }
            if(isValid(x2 + 1, y2 + 1)) {
                c = solve(x1 + 1, y1, x2 + 1, y2 + 1, grid);
            }
            d2 = Math.max(a, Math.max(b, c));
        }
        if(isValid(x1 + 1, y1 + 1)) {
            
            int a = 0,b = 0,c = 0;
            // 3 moves for robot 2
            if(isValid(x2 + 1, y2 - 1)) {
                a = solve(x1 + 1, y1 + 1, x2 + 1, y2 - 1, grid);
            }
            if(isValid(x2 + 1, y2)) {
                b = solve(x1 + 1, y1 + 1, x2 + 1, y2, grid);
            }
            if(isValid(x2 + 1, y2 + 1)) {
                c = solve(x1 + 1, y1 + 1, x2 + 1, y2 + 1, grid);
            }
            d3 = Math.max(a, Math.max(b, c));
        }
        grid[x2][y2] = t2;
        grid[x1][y1] = t1;

        int res = t1 + t2 + Math.max(d1, Math.max(d2, d3));
        // System.out.println(x1 + "::" + y1 + "::" + x2 + "::" + y2 + ":::::" + res);
        dp.put(ks, res);
        return res;
    }
    private static boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}