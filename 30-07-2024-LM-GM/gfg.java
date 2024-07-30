
class Solution {
    private static ArrayList<String> ans;
    private static boolean[][] vis;
    private static int[][] dir = new int[][] {{0,-1}, {0,1}, {1, 0}, {-1,0}};
    public ArrayList<String> findPath(int[][] mat) {
        // Your code here
        int n = mat.length, m = mat[0].length;
        vis = new boolean[n][m];
        ans = new ArrayList<>();
        if (mat[0][0] == 0) return ans;
        vis[0][0] = true;
        solve(mat, 0, 0, new StringBuilder());
        return ans;
    }
    private static void solve (int[][] mat, int x, int y, StringBuilder sb) {
        if (x == mat.length - 1 && y == mat[0].length - 1) {
            if (mat[x][y] == 1) {
                ans.add(sb.toString());
            }
            return;
        }

        for(int i = 0; i < dir.length; i++) {
            int xx = dir[i][0] + x, yy = dir[i][1] + y;
            StringBuilder old = new StringBuilder(sb);
            if ( xx >= 0 && yy >= 0 && xx < mat.length && yy < mat[0].length && mat[xx][yy] == 1 && !vis[xx][yy]) {
                vis[xx][yy] = true;
                if (i == 0)         sb.append("L");
                else if (i == 1)    sb.append("R");
                else if (i == 2)    sb.append("D");
                else                sb.append("U");
                solve(mat, xx, yy, sb);

                // rollback
                vis[xx][yy] = false;
                sb = old;
            }
        }
    }
}